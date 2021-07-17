package org.anon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 
 * @author averma
 *
 */
public class ListExample {
	public static void main(String[] args) {		
		ListExample listEx = new ListExample();
		listEx.ex();		
	}
	
	public void ex() {
		List<String> list = new ArrayList<>();
		
		list.add("John");
		list.add("Mary");
		list.add("Steve");
		list.add("Abraham");
		list.add("Bill");
		list.add("Donald");
		list.add("Joe");
		list.add("Angela");
		//print all items in list
		list.stream().forEach(val -> System.out.print(val+","));
		
		System.out.println();
		//print items starting with A
		list.stream().filter(val -> val.startsWith("A")).forEach(val -> System.out.print(val+","));
		System.out.println();
		System.out.println(list.stream().allMatch(val-> val.contains("m")));
		
		System.out.println(list.stream().anyMatch(val -> val.startsWith("M")));
		System.out.println();
		list.stream().filter(val -> val.startsWith("P")).collect(Collectors.toList()).forEach(v -> System.out.print(v+","));
		System.out.println();
		System.out.println(list.stream().count());		
		System.out.println(list.stream().distinct().count());		
		System.out.println(list.stream().filter(v -> v.endsWith("i")).findAny());
		System.out.println(list.stream().findFirst().get());
		int[][] arr2d = {{1,2}, {5,6}, {7,8}, {9,10}};
		String[][] multiArray = {{"1","2","3"},{"4","5","6"}};
		String[] strings = Arrays.stream(multiArray)
		        .flatMap(Arrays::stream)
		        .toArray(size -> new String[size]);
		list.stream().limit(2).forEach(System.out::print);
		System.out.println();
		list.stream().map(v-> v.length()*3).forEach(System.out::print);
		System.out.println();
		list.forEach(v -> System.out.print(v.length()*5));
		System.out.println();
		list.stream().mapToInt(v -> v.length()).forEach(w->System.out.print(w*w));
		System.out.println();
		list.stream().map(v -> v.length()).forEach(w->System.out.print(w*w));
		System.out.println();
		System.out.println(list.stream().max((a,b) -> a.length() - b.length()).get());
		System.out.println(list.stream().min((a,b) -> a.compareTo(b)).get());
		System.out.println(list.stream().noneMatch(a-> a.equals("Anila")));
		
		List<Integer> intList = new ArrayList<>();
		intList.add(1);
		intList.add(2);
		intList.add(3);
		intList.add(4);
		intList.add(5);
		intList.add(6);		
		System.out.println(intList.stream().reduce(1,(a,b) -> a*b));
		System.out.println(intList.stream().reduce(Integer::max).get());
		list.stream().skip(1).forEach(System.out::print);
		System.out.println();
		list.stream().sorted((a,b) -> a.length()-b.length()).forEach(System.out::print);
		System.out.println();
		list.stream().sorted((a,b) -> a.compareTo(b)).forEach(System.out::print);
		System.out.println();
		System.out.println(list.stream().toArray().length);
		

	}
	
}
