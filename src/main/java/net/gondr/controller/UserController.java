package net.gondr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.gondr.domain.GondrVO;
import net.gondr.validator.GondrValidator;

@Controller
@RequestMapping("/user/")
public class UserController {
	@RequestMapping(value = "regist", method = RequestMethod.GET)
	public String viewRegistPage() {
		return "user/regist";
	}

	@RequestMapping(value = "regist", method = RequestMethod.POST)
	public String registProcess(GondrVO go, Errors errors, Model model) {

		GondrValidator vali = new GondrValidator();
		vali.validate(go, errors);
		
		if(errors.hasErrors()) {
			model.addAttribute("errors", errors);
			return "error"; //에러가 존재한다면 에러페이지로
		}
		
		return "user/regist_ok";
	}
}
