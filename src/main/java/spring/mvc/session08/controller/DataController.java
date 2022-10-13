package spring.mvc.session08.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/data")
public class DataController {

	//執行路徑 : /mvc/data/case1
	//@RequestMapping(value = "/case1", method = RequestMethod.GET)
	@GetMapping(value = "/case1")
	public ModelAndView case1() {
		String data = "Hello data1"; // 資料 Model
		//String view = "/WEB-INF/view/session08/show_data.jsp"; // 資料呈現地(資料渲染位置) 必須根據 springmvc-servlet.xml 配置進行修改路徑位置
		String view = "session08/show_data";
		// 建立 ModelAndView 目的 : 將 data(model) 與 view 封裝起來
		ModelAndView mv = new ModelAndView();
		mv.addObject("data1",data);
		mv.setViewName(view);
		return mv;
	}
	
	//執行路徑 : /mvc/data/case2
	//@RequestMapping(value = "/case1", method = RequestMethod.GET)
	@GetMapping(value = "/case2")
	public ModelAndView case2() {
		String data = "Hello data2"; // 資料 Model
		//String view = "/WEB-INF/view/session08/show_data.jsp"; // 資料呈現地(資料渲染位置) 必須根據 springmvc-servlet.xml 配置進行修改路徑位置
		String view = "session08/show_data";
		// 建立 ModelAndView 目的 : 將 data(model) 與 view 封裝起來
		return new ModelAndView(view, "data2",data);
	}
	
	//執行路徑 : /mvc/data/case3
	//@RequestMapping(value = "/case1", method = RequestMethod.GET)
	@GetMapping(value = "/case3")
	public ModelAndView case3() {
		Map<String, String> map = new LinkedHashMap<>();
		map.put("data1", "Hello data1");
		map.put("data2", "Hello data2");
		map.put("data3", "Hello data3");
		//String view = "/WEB-INF/view/session08/show_data.jsp"; // 資料呈現地(資料渲染位置) 必須根據 springmvc-servlet.xml 配置進行修改路徑位置
		String view = "session08/show_data";
		// 建立 ModelAndView 目的 : 將 data(model) 與 view 封裝起來
		ModelAndView mv = new ModelAndView();
		mv.addAllObjects(map);
		mv.setViewName(view);
		return mv;
	}
}
