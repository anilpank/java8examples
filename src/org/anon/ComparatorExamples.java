package org.anon;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ComparatorExamples {
	final List<Person> people = Arrays.asList(
			new Person("John", 20),
			new Person("Sara", 21),
			new Person("Jane", 21),
			new Person("Greg", 35),
			new Person("Ash", 17));
	
	final Comparator<Person> compareAscending = 
			(person1, person2) -> person1.ageDifference(person2);
	final Comparator<Person> compareDescending = 
			compareAscending.reversed();
	final Function<Person, String> byName = Person::getName;	
	final Function<Person, Integer> byAge = Person::getAge;
	
	
	public static void main(String[] args) {
		System.out.println("...........Comparators............");
		ComparatorExamples comparators = new ComparatorExamples();
		comparators.ascending();
		comparators.descending();
		comparators.ascending1();
		comparators.descending1();
		comparators.youngest();
		comparators.eldest();
		comparators.ascendingName();
		comparators.ascendingAgeThenName();
		comparators.olderThan20();
		comparators.groupByAge();
		comparators.peopleNamesGroupedByAge();
		comparators.complexGrouping();
	}
	
	public void ascending() {
		List<Person> ascendingAge = 
				people.stream()
				.sorted((person1, person2) -> person1.ageDifference(person2))
				.collect(Collectors.toList());
		System.out.println(ascendingAge);
	}
	
	
	public void descending() {
		System.out.println(people.stream()
				.sorted((person1, person2) -> person2.ageDifference(person1))
				.collect(Collectors.toList())
				);		
	}
	
	public void ascending1() {
		System.out.println(people.stream()
				.sorted(compareAscending)
				.collect(Collectors.toList()));		
	}
	
	public void descending1() {
		System.out.println(people.stream()
				.sorted(compareDescending)
				.collect(Collectors.toList())
				);
	}
	
	public void youngest() {
		people.stream()
		.min(Person::ageDifference)
		.ifPresent(youngest -> System.out.println("Youngest:" + youngest));
	}
	
	public void eldest() {
		people.stream()
		.max(Person::ageDifference)
		.ifPresent(eldest -> System.out.println("Eldest:" + eldest));
	}
	
	public void ascendingName() {
		System.out.println(people.stream()
				.sorted(Comparator.comparing(byName))
				.collect(Collectors.toList()));		
	}
	
	public void ascendingAgeThenName() {
		System.out.println("Sort in ascending order by age and then name");		
		System.out.println(people.stream()
				.sorted(Comparator.comparing(byAge)
						.thenComparing(byName))
						.collect(Collectors.toList()));		
	}
	
	public void olderThan20() {
		System.out.println("People older than 20");
		System.out.println(people.stream()
				.filter(person -> person.getAge()>20)
				.collect(Collectors.toList()));		
	}
	
	public void groupByAge() {
		System.out.println("Grouping people by age");
		System.out.println(people.stream()
				.collect(Collectors.groupingBy(Person::getAge)));		
	}
	
	public void peopleNamesGroupedByAge() {
		System.out.println("People's names grouped by age");		
		System.out.println(people.stream()
				.collect(Collectors.groupingBy(Person::getAge, 
						Collectors.mapping(Person::getName, Collectors.toList()))));		
	}
	
	public void complexGrouping() {
		System.out.println("Group the names by their first character and then get oldest person in each group");
		System.out.println(people.stream()
				.collect(Collectors.groupingBy(person -> person.getName().charAt(0), 
						Collectors.reducing(BinaryOperator.maxBy(Comparator.comparing(byAge))))));		
	}
}
