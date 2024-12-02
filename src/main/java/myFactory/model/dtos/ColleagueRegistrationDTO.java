package myFactory.model.dtos;

public class ColleagueRegistrationDTO {
    private String firstName;
    private String lastName;
    private int age;
    private String password;
    private String confirmPassword;
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
