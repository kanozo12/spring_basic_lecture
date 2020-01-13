package net.gondr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MainController {
	//path value -> path parameter
	@GetMapping("/main/{name}")
	public String hello(Model model, @PathVariable String name){
		model.addAttribute("msg", "안녕하세요 ! " + name);
		return "index";
	}
	
//	@ExceptionHandler(TypeMismatchException.class)
//	public String handleTypeMismatch() {
//		return "exception";
//	}
}
