package org.anon;

import java.awt.Color;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * Decorating using Lambda expressions
 * @author averma
 *
 */
public class DecoratorShade {
	private Function<Color, Color> filter;

	public Color capture(final Color inputColor) {
		final Color processedColor = filter.apply(inputColor);
		// more processing of color
		return processedColor;
	}

	public void setFilters(final Function<Color, Color>... filters) {
		filter = Stream.of(filters)
				.reduce((filter, next) -> filter.compose(next))
				.orElse(color->color);
	}

	public DecoratorShade() {
		setFilters();
	}

	// other functions that use the filter


	public static void main(String [] args) {
		final DecoratorShade camera = new DecoratorShade();
		
		// To see shade in action, we need a convenience function to print capture method's results
		//Rather than creating stand alone static method, we created a lambda expression
		//We chose a Consumer because printing consumes a value and does not yield any results
		final Consumer<String> printCaptured = (filterInfo) ->
		System.out.println(String.format("with %s: %s", filterInfo, camera.capture(
				new Color(200, 100, 200))));
		printCaptured.accept("No Filter");
		
		//Adding brighter filter
		camera.setFilters(Color::brighter);
		printCaptured.accept("Brighter");
		
		//Adding darker filter
		camera.setFilters(Color::darker);
		printCaptured.accept("Darker");
		
		//Adding multiple filters
		camera.setFilters(Color::brighter, Color::darker);
		printCaptured.accept("Brighter and Darker");
		
		//We designed object chaining and implemented the decorator pattern without having to create multiple 
		//class hierarchies.
	}
}
