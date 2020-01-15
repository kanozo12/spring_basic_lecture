package net.gondr.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import net.gondr.dao.UserDAO;
import net.gondr.domain.GondrVO;
import net.gondr.domain.LoginDTO;
import net.gondr.domain.UserVO;
import net.gondr.validator.GondrValidator;

@Controller
@RequestMapping("/user/")
public class UserController {
	@Autowired
	private UserDAO dao;

	@RequestMapping(value = "regist", method = RequestMethod.GET)
	public String viewRegistPage() {
		return "user/regist";
	}

	@RequestMapping(value = "regist", method = RequestMethod.POST)
	public String registProcess(GondrVO go, Errors errors, Model model) {
		GondrValidator vali = new GondrValidator();
		vali.validate(go, errors);
		if (errors.hasErrors()) {
			model.addAttribute("errors", errors);
			return "error"; // 에러가 존재하면 에러페이지로 이동해라.
		}

		return "user/registok";
	}

	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String viewLoginPage(Model model, @CookieValue(value = "REMEMBER", required = false) Cookie cookie) {
		if (cookie != null) {
			model.addAttribute("email", cookie.getValue());
		}
		return "user/login";
	}

	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String loginProcess(LoginDTO login, HttpSession session, HttpServletResponse res) {
		if (login.getUserid().equals("gondr") && login.getPassword().equals("1234")) {
			UserVO user = new UserVO();
			user.setUserid(login.getUserid());
			user.setUsername("최선한");
			// 여기에 만약 remember가 true이면 쿠키에 해당 값을 기억하게 만들 예정
			Cookie cookie = new Cookie("REMEMBER", login.getUserid());
			cookie.setPath("/");

			if (login.isRemember()) {
				cookie.setMaxAge(60 * 60 * 24 * 30); // 30일간 기억하는 쿠키
			} else {
				cookie.setMaxAge(0); // 바로 삭제
			}
			res.addCookie(cookie); // 쿠키를 응답에다가 더해주고
			session.setAttribute("user", user);

			return "redirect:/";
		} else {
			return "redirect:/user/login";
		}
	}

	// AOP => 관점지향 프로그래
	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		if (session.getAttribute("user") != null) {
			session.removeAttribute("user");
			return "redirect:/";
		} else {
			return "redirect:/user/login";
		}
	}

	@RequestMapping(value = "info", method = RequestMethod.GET)
	public String viewInfoPage() {
		return "user/info";
	}

	@RequestMapping(value = "data/{userid}", method = RequestMethod.GET)
	public @ResponseBody UserVO getUserData(@PathVariable String userid) {
		UserVO user = dao.selectUser(userid);
		return user;
	}

}
