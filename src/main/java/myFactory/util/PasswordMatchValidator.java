package myFactory.util;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;

public class PasswordMatchValidator implements ConstraintValidator<PasswordMatchValidatorInterface, Object> {
    private String password;
    private String confirmPassword;

    @Override
    public void initialize(PasswordMatchValidatorInterface constraintAnnotation) {
        this.password = constraintAnnotation.password();
        this.confirmPassword = constraintAnnotation.confirmPassword();
    }

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext constraintValidatorContext) {
        BeanWrapper beanWrapper = PropertyAccessorFactory.forBeanPropertyAccess(object);
        Object passwordValue = beanWrapper.getPropertyValue(password);
        Object confirmPasswordValue = beanWrapper.getPropertyValue(confirmPassword);
        System.out.println("Password: " + passwordValue + ", Confirm Password: " + confirmPasswordValue);
        if (passwordValue == null || !passwordValue.equals(confirmPasswordValue)) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate("Passwords must match!")
                    .addPropertyNode(confirmPassword)
                    .addConstraintViolation();
            return false; // Validation failed
        }

        return true; // Validation passed
    }
}
