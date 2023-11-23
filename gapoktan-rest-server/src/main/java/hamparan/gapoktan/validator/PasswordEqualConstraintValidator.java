package hamparan.gapoktan.validator;

import hamparan.gapoktan.dto.request.UserRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordEqualConstraintValidator implements ConstraintValidator<PasswordEqualConstraint, Object> {

  @Override
  public boolean isValid(Object data, ConstraintValidatorContext context) {
    UserRequest userRequest = (UserRequest) data;
    return userRequest.getPassword().equals(userRequest.getPassword_retype());
  }

}
