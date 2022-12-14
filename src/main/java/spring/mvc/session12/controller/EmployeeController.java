package spring.mvc.session12.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import spring.mvc.session12.entity.Employee;
import spring.mvc.session12.repository.EmployeeDao;

@Controller
@RequestMapping(value = "/jdbc/employee")
public class EmployeeController {

	@Autowired
	private EmployeeDao employeeDao;
	
	private int getPageCount() {
		int pagecount = (int)Math.ceil((double)employeeDao.getcount()/employeeDao.LIMIT);
		return pagecount;
	}
	
	@GetMapping(value = "/")
	public String index(Model model , @ModelAttribute Employee employee, HttpSession session) {
//		model.addAttribute("_method","POST");
//		model.addAttribute("employees", employeeDao.query());
//		model.addAttribute("pageCount",getPageCount());
//		return "session12/employee";
		int num = 1;
		if(session.getAttribute("num") != null) {
			num = (Integer)(session.getAttribute("num"));
		}
		return page(employee, num, model, session);
	}
	
	@GetMapping(value = "/{eid}")
	public String get(@PathVariable("eid")Integer eid, Model model, HttpSession session) {
		int num = (Integer)(session.getAttribute("num"));
		int offset = (Integer)(session.getAttribute("offset"));
		model.addAttribute("_method","PUT");
		model.addAttribute("employees", employeeDao.queryPage(offset));
		model.addAttribute("employee", employeeDao.getById(eid));
		model.addAttribute("pageCount",getPageCount());
		model.addAttribute("pageNum",num);
		return "session12/employee";
	}
	
	@GetMapping(value = "/page/{num}")
	public String page(@ModelAttribute Employee employee, @PathVariable("num")Integer num, Model model , HttpSession session) {
		if(num > getPageCount()) {
			throw new RuntimeException("?????????");
		}
		int offset = (num-1) * EmployeeDao.LIMIT;
		//??? num , offset ????????? session ?????????
		session.setAttribute("num", num);
		session.setAttribute("offset", offset);
		// -------------------------------------------
		model.addAttribute("_method","POST");
		model.addAttribute("employees", employeeDao.queryPage(offset));
		model.addAttribute("pageCount",getPageCount());
		model.addAttribute("pageNum",num);
		return "session12/employee";
	}
	
	@PostMapping(value = "/")
	public String add(@Valid @ModelAttribute Employee employee , BindingResult result , Model model, HttpSession session) {
		int num = (Integer)(session.getAttribute("num"));
		int offset = (Integer)(session.getAttribute("offset"));
		if(result.hasErrors()) {
			model.addAttribute("_method","POST");
			model.addAttribute("employees", employeeDao.queryPage(offset));
			model.addAttribute("pageCount",getPageCount());
			model.addAttribute("pageNum",num);
			return "session12/employee"; // forward
		}
		employeeDao.add(employee);
		session.setAttribute("num", getPageCount());
		return "redirect:./";
	}
	
	@PutMapping(value = "/")
	public String update(@Valid @ModelAttribute Employee employee, BindingResult result , Model model, HttpSession session) {
		int num = (Integer)(session.getAttribute("num"));
		int offset = (Integer)(session.getAttribute("offset"));
		if(result.hasErrors()) {
			model.addAttribute("_method","PUT");
			model.addAttribute("employees", employeeDao.queryPage(offset));
			model.addAttribute("pageCount",getPageCount());
			model.addAttribute("pageNum",num);
			return "session12/employee";
		}
		employeeDao.update(employee);
		return "redirect:./";
	}
	
	@DeleteMapping(value = "/")
	public String delete(Employee employee, HttpSession session) {
		employeeDao.delete(employee.getEid());
		int num = (Integer)(session.getAttribute("num"));
		if(num > getPageCount()) {
			session.setAttribute("num", getPageCount());
		}
		return "redirect:./";
	}
	

	// ???????????????????????????
	@ExceptionHandler({NumberFormatException.class,RuntimeException.class, MethodArgumentTypeMismatchException.class})
	public String fixed(Exception ex, Model model, HttpServletRequest request) {
		model.addAttribute("location", "/spring.mvc/mvc/jdbc/employee/");
		model.addAttribute("ex",ex); // ????????????
		return "session12/error";
	}
}
