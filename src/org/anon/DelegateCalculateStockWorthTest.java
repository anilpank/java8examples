package org.anon;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.math.BigDecimal;

/**
 * 
 * @author averma
 *
 */
public class DelegateCalculateStockWorthTest {
	
	@Test
	public void computeStockWorth() {
		final DelegateCalculateStockWorth calculateNav = 
				new DelegateCalculateStockWorth(ticker -> new BigDecimal("6.01"));
		BigDecimal expected = new BigDecimal("6010.00");
		assertEquals(0, calculateNav.computeStockWorth("GOOG", 1000)
				.compareTo(expected));		
	}
	
	
	@Test
	public void computeStock() {
		final DelegateCalculateStockWorth calculateNav = 
				new DelegateCalculateStockWorth(YahooFinance::getPrice);
		System.out.println(calculateNav.computeStockWorth("GOOG", 1000));
		
	}

}
