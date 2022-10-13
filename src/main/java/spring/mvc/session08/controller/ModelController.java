package spring.mvc.session08.controller;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import spring.mvc.session08.entity.Person;

@Controller
@RequestMapping(value = "/model")
public class ModelController {

	
	// 執行路徑 : http://localhost:8080/spring.mvc/mvc/model/case1
	// 宣告 model 參數 用來放置資料 傳給 view 的資料
	// 回傳值 String 指的就是 view 的路徑 (配合 springmvc-servlet.xml 的設定)
	@GetMapping(value = "/case1")
	public String case1(Model model) {
		model.addAttribute("data1" , "Hello Model1");
		model.addAttribute("data2" , "Hello Model2");
		model.addAttribute("data3" , "Hello Model3");
		return "session08/show_data";
	}
	
	// model 是陣列/集合或物件資料 : List, Map, Person
	
	@GetMapping(value = "/case2")
	public String case2(Model model) {
		List<String> names = Arrays.asList("John","Mary","Helen","Amy","Bob");
		Map<String, Integer> fruits = new LinkedHashMap<String, Integer>();
		fruits.put("apple", 50);
		fruits.put("banana", 30);
		Person person = new Person();
		person.setName("Tom");
		person.setAge(18);
		person.setScore(100.0);
		person.setPass(true);
		model.addAttribute("data1", names);
		model.addAttribute("data2", fruits);
		model.addAttribute("data3", person);
		return "session08/show_data";
	}
	
	// 重定向
	@GetMapping(value = "/case3")
	public String case3(Model model) {
		//String path = "http://tw.yahoo.com"; // 到外網
		String path = "/index.jsp"; // 到內網
		//String path = "/mvc/hello/welcome"; //重定向到某個 controller
		//String path = "./case1"; //重定向到本身 controller 的某個方法    (. 代表當下路徑)
		return "redirect:" + path;
	}
}
