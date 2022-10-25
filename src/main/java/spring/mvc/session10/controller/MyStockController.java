package spring.mvc.session10.controller;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import spring.mvc.session11.entity.MyStock;
import spring.mvc.session11.validator.MyStockValidator;

@Controller
@RequestMapping(value = "/mystock")
public class MyStockController {
	
	private List<MyStock> myStocks = new CopyOnWriteArrayList<MyStock>();
		
	@Autowired
	private MyStockValidator myStockValidator; //自訂錯誤驗證
	
	@GetMapping(value = "/")
	public String index(Model model, @ModelAttribute MyStock myStock) {
		model.addAttribute("mystocks",myStocks);
		return "session11/mystock";
	}

	@PostMapping(value = "/")
	public String add(Model model, @ModelAttribute @Valid MyStock myStock, BindingResult result) {
		//配置自訂錯誤驗證
		myStockValidator.validate(myStock, result);
		if(result.hasErrors()) {
			model.addAttribute("mystocks", myStocks);
			return "session11/mystock";
		}
		myStocks.add(myStock);
		return "redirect:./";
	}
}
