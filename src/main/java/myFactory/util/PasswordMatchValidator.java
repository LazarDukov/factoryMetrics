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
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        BeanWrapper beanWrapper = PropertyAccessorFactory.forBeanPropertyAccess(value);
        Object passwordValue = beanWrapper.getPropertyValue(this.password);
        Object confirmPasswordValue = beanWrapper.getPropertyValue(this.confirmPassword);

        if (passwordValue == null || confirmPasswordValue == null) {
            return false;
        }

        boolean isValid = passwordValue.equals(confirmPasswordValue);
        if (!isValid) {
            context.buildConstraintViolationWithTemplate("Password should be the same!").addPropertyNode(confirmPassword).addConstraintViolation();
        }
        return isValid;
    }
}
