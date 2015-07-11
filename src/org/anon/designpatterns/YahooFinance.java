package org.anon.designpatterns;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URL;

public class YahooFinance {
	
	public static BigDecimal getPrice(final String ticker) {
		try {
			final URL url = 
					new URL("http://ichart.finance.yahoo.com/table.csv?s=" + ticker);
			final BufferedReader reader = 
					new BufferedReader(new InputStreamReader(url.openStream()));
			final String data = reader.lines().skip(1).findFirst().get();
			final String[] dataItems = data.split(",");
			return new BigDecimal(dataItems[dataItems.length - 1]);			
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void main(String [] args) {
		System.out.println(YahooFinance.getPrice(""));
	}
}
