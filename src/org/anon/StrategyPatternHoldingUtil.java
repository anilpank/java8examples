package org.anon;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import org.anon.Holding.HoldingType;

/**
 * This class demonstrates on how to do separating of concerns with Lambda expressions.
 * Strategy pattern example without creating your own interfaces
 * @author averma
 *
 */
public class StrategyPatternHoldingUtil {
	
	/**
	 * Problem here
	 * Getting entangled with concerns
	 * How to iterate, what to total, how to total
	 * @param assets
	 * @return
	 */
	public static int totalHoldingValues(final List<Holding> assets) {
		return assets.stream()
				.mapToInt(Holding::getValue)
				.sum();
	}
	
	/**
	 * Only difference between totalAssetValues and totalBondValues is the lambda expression
	 * that we send to mapToInt
	 * @param assets
	 * @return
	 */
	public static int totalBondValues(final List<Holding> assets) {
		return assets.stream()
				.mapToInt(asset -> asset.getType() == HoldingType.BOND ? asset.getValue() : 0)
				.sum();
	}
	
	/**
	 * Using strategy pattern
	 * @param assets - list of assets
	 * @param assetSelector - predicate to evaluate whether an asset should be considered
	 * Rather than creating our own interface, we have resued java.util.function.Predicate interface
	 * @return
	 */
	public static int totalHoldingValues(final List<Holding> assets, 
			final Predicate<Holding> assetSelector) {
		return assets.stream()
				.filter(assetSelector)
				.mapToInt(Holding::getValue)
				.sum();
	}
	
	
	
	public static void main (String [] args) {
		final List<Holding> assets = Arrays.asList(
				new Holding(Holding.HoldingType.BOND, 1000),
				new Holding(Holding.HoldingType.BOND, 2000),
				new Holding(Holding.HoldingType.STOCK, 3000),
				new Holding(Holding.HoldingType.STOCK, 4000)
				);
		System.out.println("Total asset Values:" + totalHoldingValues(assets));
		System.out.println("Total of all assets:" + totalHoldingValues(assets, asset->true));
		System.out.println("Total of bonds:" +  totalHoldingValues(assets, asset-> asset.getType() == HoldingType.BOND));
		System.out.println("Total of stocks:" +  totalHoldingValues(assets, asset-> asset.getType() == HoldingType.STOCK));
	}
	
	
}
