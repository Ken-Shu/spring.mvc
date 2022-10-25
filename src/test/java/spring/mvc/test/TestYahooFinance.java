package spring.mvc.test;

import org.junit.Test;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;

public class TestYahooFinance {
	
	@Test
	public void test() throws Exception{
		Stock yahooFinance = YahooFinance.get("2330.TW");
		System.out.println(yahooFinance);
		
	}
}
