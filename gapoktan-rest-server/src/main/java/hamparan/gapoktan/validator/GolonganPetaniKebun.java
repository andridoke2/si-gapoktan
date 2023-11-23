package hamparan.gapoktan.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({ ElementType.FIELD, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = GolonganPetaniKebunValidator.class)
public @interface GolonganPetaniKebun {

  String message();

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

}