package myFactory.util;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Constraint(validatedBy = PasswordMatchValidator.class)
public @interface PasswordMatchValidatorInterface {
    String password();

    String confirmPassword();

    String message() default "Passwords should be the same!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
