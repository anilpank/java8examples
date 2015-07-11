package org.anon.designpatterns;

import java.math.BigDecimal;
import java.util.function.Function;

/**
 * Delegating using Lambda expressions
 * @author averma
 *
 */
public class DelegateCalculateStockWorth {
	private Function<String, BigDecimal> priceFinder;
	
	
	/**
	 * Initialize priceFinder through constructor injection using dependency injection and dependency inversion principle
	 * @param priceFinder
	 */
	public DelegateCalculateStockWorth(final Function<String, BigDecimal> priceFinder) {
		this.priceFinder = priceFinder;
	}
	
	/**
	 * Request price of a ticker from priceFinder
	 * What kind of Object, should priceFinder be, one who gets ticker symbol as input and gives price
	 * java.util.function.Function<T,R> functional interface is a good fit. It's an abstract method that
	 * can take a value and return another value. Let's define this field
	 * @param ticker
	 * @param shares
	 * @return
	 */
	public BigDecimal computeStockWorth(final String ticker, final int shares) {
		return priceFinder.apply(ticker).multiply(BigDecimal.valueOf(shares));
	}
	
}
