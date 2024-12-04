package myFactory.model.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import myFactory.util.PasswordMatchValidatorInterface;

@PasswordMatchValidatorInterface(password = "password", confirmPassword = "confirmPassword", message = "PASSWORD SHOULD MATCH!")
public class ColleagueRegistrationDTO {
    @NotBlank(message = "Your first name cannot be empty!")
    @Size(min = 2, max = 15)
    private String firstName;
    @NotBlank(message = "Your last name cannot be empty!")
    @Size(min = 2, max = 15)
    private String lastName;
    @NotBlank
    private int age;
    @NotBlank(message = "Password is required")
    @Size(min = 6, max = 20)
    private String password;
    @NotBlank(message = "Password is required")
    @Size(min = 6, max = 20)
    private String confirmPassword;
    @NotBlank
    private String role;

    public ColleagueRegistrationDTO(String firstName, String lastName, int age, String password, String confirmPassword, String role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.role = role;
    }

    public String getFirstName() {
        return firstName;
    }

    public ColleagueRegistrationDTO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public ColleagueRegistrationDTO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public int getAge() {
        return age;
    }

    public ColleagueRegistrationDTO setAge(int age) {
        this.age = age;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public ColleagueRegistrationDTO setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public ColleagueRegistrationDTO setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }

    public String getRole() {
        return role;
    }

    public ColleagueRegistrationDTO setRole(String role) {
        this.role = role;
        return this;
    }
}
