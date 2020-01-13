package net.gondr.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import net.gondr.domain.GondrVO;

public class GondrValidator implements Validator {
	@Override
	public boolean supports(Class<?> clazz) {
		return GondrVO.class.isAssignableFrom(clazz); // gondr 클래스에 할당 가능하니? 가능 : true 불 : false
	}

	@Override
	public void validate(Object target, Errors errors) { // 모두 객체 target : GondrVO 클래스
		// call by reference
		// call by value
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userid", "required", "유저 아이디 값은 필수 입니다.");

		GondrVO vo = (GondrVO) target;

		if (vo.getCode() < 0) {
			errors.rejectValue("code", "bad", "0이하의 숫자는 사용할 수 없습니다.");
		}
	}
}
