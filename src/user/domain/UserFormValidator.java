package user.domain;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class UserFormValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return UserForm.class.isAssignableFrom(clazz);
	}
	
	@Override
	public void validate(Object obj, Errors errors) {
		UserForm userForm = (UserForm)obj;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id", "field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "subName", "field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phoneNumber", "field.required");
																																																																						
	}
	
}
