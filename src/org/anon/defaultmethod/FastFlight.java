package org.anon.defaultmethod;

public interface FastFlight extends Flight {
	default void takeOff() { System.out.println("FastFly::takeOff"); }
}
