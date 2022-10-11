package spring.mvc.session08.controller;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.quotes.fx.FxQuote;
import yahoofinance.quotes.fx.FxSymbols;

@Controller
@RequestMapping("/hello")
public class HelloController {
	
	/*
	 * 1. 執行路徑 /mvc/hello/welcome
	 * /mvc 在web.xml 中定義
	 * /hello 在 HelloController 中定義
	 * /welcome 在 welcome 方法中定義
	 *  @ResponseBody 直接將資料回應給前端(ex : 瀏覽器)
	 */
	@RequestMapping("/welcome")
	@ResponseBody
	public String welcome() {
		String message = "welcome spring mvc";
		return message;
	}
	
	/*
	 *  2. ?後方 帶參數 @RequestParam
	 *  執行路徑: /mvc/hello/sayhi?name=John&age=18
	 */
	@RequestMapping("/sayhi")
	@ResponseBody
	public String sayHi(@RequestParam(value = "name", required = false)String name,
						@RequestParam(value = "age" , defaultValue = "12")Integer age) {
		return String.format("Hi %s age = %d ", name,age);
		
	}
	
	/*
	 * 3. Lab 練習
	 * 執行路徑: /mvc/hello/bmi?h=170&w60
	 * 執行結果 bmi=20.76
	 * 請製作到對應的方法 
	 * 
	 */
	@RequestMapping(value = {"/bmi","bmi2"})
	@ResponseBody
	public String getbmi(@RequestParam(value = "height", required = false)Double height,
						@RequestParam(value = "weight" , defaultValue = "60")Double weight) {
		Double bmi = weight/Math.pow(height/100, 2); 
		return String.format("bmi = %.2f", bmi);
	}
	
	/*
	 *  4. 同名多參數的資料 
	 *  執行路徑: /mvc/hello/age?age=18&age=19&age=21
	 *  計算出 : 資料筆數 總和 平均 最大 最小值
	 */
	@RequestMapping(value = "/age")
	@ResponseBody
	public String age(@RequestParam(value = "age", required = false)List<Integer> age) {
		// int 的統計物件
		IntSummaryStatistics ists = age.stream().mapToInt(Integer::intValue).summaryStatistics();
		
		return String.format("資料比數 : %d <br>總和 : %d <br>平均 : %.1f <br>最大值 : %d <br>最小值 : %d",
				age.size(),ists.getSum(),ists.getAverage(),ists.getMax(),ists.getMin());
	}
	
	/*
	 *  4. Lab 取的多檔股價與匯率
	 *  執行路徑: /mvc/hello/symbol?symbol=2330.TW&symbol=2317.TW&symbol=USDTWD=x&symbol=JPYTWD=x
	 *  印出每個商品股價與匯率資料
	 */
	@RequestMapping(value = "/symbol")
	@ResponseBody
	public String symbol(@RequestParam(value = "symbol", required = false)String[] symbols) {		
		Map<String, Stock> stocks = null;
		FxQuote usdeur = null;
		FxQuote usdtwd = null;
		FxQuote jpytwd = null;
		try {
			stocks = YahooFinance.get(symbols); // single request
			usdeur = YahooFinance.getFx(FxSymbols.USDEUR);
			usdtwd = YahooFinance.getFx("USDTWD=x");	
			jpytwd = YahooFinance.getFx("JPYTWD=x");	
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return String.format("%s <br>  %s <br> %s", stocks.toString().replace(",", "<br>").replace("{", "").replace("}", "")
				,usdtwd,jpytwd);
	}
}
