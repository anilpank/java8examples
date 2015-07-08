package org.anon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;



public class CollectionExamples {
	private final List<String> friends = Arrays.
			asList("Brian", "Nate", "Neal", "Raju", "Sara", "Scott");

	final List<String> editors =
			Arrays.asList("Brian", "Jackie", "John", "Mike");
	final List<String> comrades =
			Arrays.asList("Kate", "Ken", "Nick", "Paula", "Zach");

	final Predicate<String> startsWithN = name -> name.startsWith("N");


	public static void main(String[] args) {
		Runnable r = ()->System.out.println("hello runnable");
		r.run();

		String[] words = {"aaa", "b", "cc"};
		Arrays.sort(words, (s1, s2) -> s1.length() - s2.length());
		for (String word : words) {
			System.out.println(word);
		}
		CollectionExamples ex = new CollectionExamples();

		//ex.iterate3();
		ex.manipulate3();
		ex.printNameLengths();
		//ex.find1();
		ex.find2();
		ex.multiFind();
		final List<String> names = Arrays.
				asList("Brian", "Nate", "Neal", "Raju", "Sara", "Scott");
		ex.pickName1(names, "N");
		ex.pickName1(names, "P");
		ex.totalNumChars();
		ex.longestName();
		ex.regularJoin();
		ex.join1();
		ex.join2();
	}

	public void multiFind() {
		long nFriendCount = friends.stream()
				.filter(startsWithN)
				.count();
		long nEditorCount = editors.stream()
				.filter(startsWithN)
				.count();
		long nComradesCount = comrades.stream()
				.filter(startsWithN)
				.count();
		System.out.println(nFriendCount);
		System.out.println(nEditorCount);
		System.out.println(nComradesCount);
	}

	public void manipulate1() {
		final List<String> uppercaseNames = new ArrayList<String>();		
		for(String name : friends) {
			uppercaseNames.add(name.toUpperCase());
		}
		System.out.println(uppercaseNames);
	}

	public void manipulate2() {
		friends.stream()
		.map(name->name.toUpperCase())
		.forEach(name -> System.out.print(name + " "));
		System.out.println();
	}

	public void manipulate3() {
		friends.stream()
		.map(String::toUpperCase)
		.forEach(name -> System.out.print(name + " "));
		System.out.println();
	}

	public void printNameLengths() {
		friends.stream()
		.map(String::length)
		.forEach(count -> System.out.print(count + " "));
		System.out.println();
	}

	public void iterate() {
		friends.forEach(new Consumer<String>(){
			public void accept(final String name) {
				System.out.println(name);
			}
		});
	}

	public void iterate1() {
		friends.forEach((final String name) -> System.out.println(name));
	}

	/**
	 * automatic type inference
	 */
	public void iterate2() {
		friends.forEach(name -> System.out.println(name));
	}

	/**
	 * Method reference
	 */
	public void iterate3() {
		friends.forEach(System.out::println);
	}

	public void find1() {
		final List<String> startsWithN = new ArrayList<String>();
		for(String name : friends) {
			if(name.startsWith("N")) {
				startsWithN.add(name);
			}
		}
		System.out.println(startsWithN);
	}

	public void find2() {
		final List<String> startsWithN = friends.stream()
				.filter(name->name.startsWith("N"))
				.collect(Collectors.toList());
		System.out.println(startsWithN);		
	}

	public static Predicate<String> checkIfStartsWith(final String letter) {
		return name-> name.startsWith(letter);
	}

	public void supreme() {
		final long countNFrnds = friends.stream()
				.filter(checkIfStartsWith("N")).count();
		final long countFriendsStartB =
				friends.stream()
				.filter(checkIfStartsWith("B")).count();
	}

	public void pickDifferentNames() {
		final Function<String, Predicate<String>> startsWithLetter = 
				(String letter) -> {
					Predicate<String> checkStarts = (String name) -> name.startsWith(letter);
					return checkStarts;
				};
				final Function<String, Predicate<String>> aStartsWithLetter = 
						(String letter) -> (String name) -> name.startsWith(letter);

						final Function<String, Predicate<String>> leanStartsWithLetter = 
								letter -> name -> name.startsWith(letter);

								final long nFriendsCount = friends.stream().filter(leanStartsWithLetter.apply("N")).count();
	}

	public static void pickName(
			final List<String> names, final String startingLetter) {
		String foundName = null;
		for(String name : names) {
			if(name.startsWith(startingLetter)) {
				foundName = name;
				break;
			}
		}

		System.out.print(String.format("A name starting with %s: ", startingLetter));
		if(foundName != null) {
			System.out.println(foundName);
		} else {
			System.out.println("No name found");
		}
	}

	public static void pickName1(final List<String> names, final String startingLetter) {
		final Optional<String> foundName = 
				names.stream()
				.filter(name -> name.startsWith(startingLetter))
				.findFirst();
		System.out.println(String.format("A name starting with %s: %s", startingLetter, foundName.orElse("No name found")));
	}

	public void totalNumChars() {
		int sum = friends.stream()
				.mapToInt(name -> name.length())
				.sum();
		System.out.println("sum of all characters:" + sum);
	}

	public void longestName() {
		final Optional<String> alongName = 
				friends.stream()
				.reduce((name1, name2) -> name1.length() >= name2.length() ? name1 : name2);
		alongName.ifPresent(name -> System.out.println("Longest name:" + alongName));
	}

	public void regularJoin() {
		for(String name : friends) {
			System.out.print(name + ", ");
		}
		System.out.println();
	}
	
	public void join1() {
		System.out.println(String.join(",", friends));
	}
	
	/**
	 * Make elements into upper case, then send a comma separated string with all names.
	 */
	public void join2() {
		System.out.println(
		friends.stream()
		.map(String::toUpperCase)
		.collect(Collectors.joining(", ")));
	}

}
