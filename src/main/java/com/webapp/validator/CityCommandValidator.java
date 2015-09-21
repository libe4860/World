package com.webapp.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.webapp.command.CityCommand;

/* command값에 대한 validation 처리 */
public class CityCommandValidator implements Validator{

	/* validation하는 객체타입 확인 */
	@Override
	public boolean supports(Class<?> clazz) {

		return CityCommand.class.isAssignableFrom(clazz);
	}

	/* validator호출 시 validation 처리 */
	@Override
	public void validate(Object target, Errors errors) {
		CityCommand city = (CityCommand) target;
		
		//validation작업(rejectValue : 필드 에러)
		
		//null인지 space공간이 있는지 에러 체크(rejectIfEmptyOrWhitespace)
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "countryCode", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "district", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "population", "required");
		
		//name 문자길이 체크
		if(city.getName().length() > 35){
			errors.rejectValue("name", "length", new Object[] {35}, "");
		}
		
		//인구입력 마이너스(-)방지 처리 
		if(city.getPopulation()<0){
			errors.rejectValue("population", "minus");
		}
		
		
		
		//에러셋팅 : 에러 무조건 넣는 작업
//		errors.rejectValue("name", "error");
//		errors.rejectValue("countryCode", "error");
//		errors.rejectValue("district", "error");
//		errors.rejectValue("population", "error");
	}

}
