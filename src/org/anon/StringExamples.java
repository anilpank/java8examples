package org.anon;

public class StringExamples {
	
	public static void main(String[] args) {
		System.out.println("Doing demo of cool string stuff in JAVA 8");
		final String demo = "NiceDemo101";
		StringExamples st = new StringExamples();
		st.iterate(demo);
		st.iterate2(demo);
		st.iterate3(demo);
		st.iterate4(demo);
		st.iterateOnlyNumbers(demo);
	}	
	
	public void iterate(final String input) {
		input.chars()
		.forEach(ch -> System.out.println(ch));
	}
	
	public void iterate2(final String input) {
		input.chars()
		.forEach(System.out::println);
	}
	
	public void iterate3(final String input) {
		input.chars()
		.forEach(StringExamples::printChar);
	}
	
	public void iterate4(final String input) {
		input.chars()
		.mapToObj(ch -> Character.valueOf((char)ch))
		.forEach(System.out::println);
	}
	
	public void iterateOnlyNumbers(final String input) {
		input.chars()
		.filter(Character::isDigit)
		.forEach(System.out::println);
	}
	
	private static void printChar (final int aChar) {
		System.out.println((char) aChar);
	}
	
	
}
