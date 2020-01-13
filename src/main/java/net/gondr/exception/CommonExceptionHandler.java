package net.gondr.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice("net.gondr.controller")
public class CommonExceptionHandler {
	@ExceptionHandler(Exception.class)
	public String handleException() {
		return "globalException";
	}
}
