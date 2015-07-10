package org.anon.defaultmethod;
/**
 * R1 Subtypes automatically carry over the default methods from their supertypes.
 * 
 * R2 For interfaces that contribute a default method, the implementation in a subtype 
 * takes precedence over the one in supertypes.
 * 
 * R3 Implementations in classes, including abstract declarations, take precedence over all interface defaults.
 * 
 * R4 If there’s a conflict between two or more default method implementations, or there’s a default-abstract 
 * conflict between two interfaces, the inheriting class should disambiguate
 * 
 * @author averma
 *
 */
public class AirShip extends AutoMobile implements FastFlight, Ship {
	
	private int altitude;
	
	public void cruise() {
		System.out.print("Seaplane::cruise currently cruise like: ");
		if (altitude > 0 ) {
			FastFlight.super.cruise();
		}
		else {
			Ship.super.cruise();
		}
	}

}
