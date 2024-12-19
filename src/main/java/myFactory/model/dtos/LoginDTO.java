package myFactory.model.dtos;

import jakarta.validation.constraints.NotBlank;

public class LoginDTO {
    @NotBlank
    private String identity;

    @NotBlank
    private String password;

    public LoginDTO(String identity, String password) {
        this.identity = identity;
        this.password = password;
    }

    public String getIdentity() {
        return identity;
    }

    public LoginDTO setIdentity(String identity) {
        this.identity = identity;
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
