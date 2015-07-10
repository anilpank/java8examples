package org.anon.defaultmethod;

public interface Ship {
	default void cruise() { System.out.println("Sail::cruise"); }
	default void turn() { System.out.println("Sail::turn"); }
}
