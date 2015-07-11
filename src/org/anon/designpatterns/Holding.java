package org.anon.designpatterns;

public class Holding {
	public enum HoldingType { BOND, STOCK };
	private final HoldingType type;
	private final int value;
	
	public Holding(final HoldingType assetType, final int assetValue) {
		type = assetType;
		value = assetValue;
	}
	
	public HoldingType getType() { return type; }
	public int getValue() { return value; }
}
