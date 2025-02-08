package myFactory.model.dtos;

import jakarta.validation.constraints.NotBlank;

public class LoginDTO {
    @NotBlank
    private String workerIdentity;

    @NotBlank
    private String password;

    public LoginDTO() {
    }

    public LoginDTO(String workerIdentity, String password) {
        this.workerIdentity = workerIdentity;
        this.password = password;
    }

    public String getIdentity() {
        return workerIdentity;
    }

    public LoginDTO setIdentity(String identity) {
        this.workerIdentity = identity;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public LoginDTO setPassword(String password) {
        this.password = password;
        return this;
    }
}
