package net.gondr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	// path value -> path parameter
	@GetMapping("/")
	public String hello() {
		return "index";
	}
}
