package hamparan.gapoktan.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = HasilKodeMatchesValidator.class)
public @interface HasilKodeMatches {

  String message();

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

}