package myFactory.api;

import myFactory.model.dtos.LoginDTO;
import myFactory.model.entities.Technician;
import myFactory.repository.TechnicianRepository;
import myFactory.service.LoginService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class LoginApiController {
    private TechnicianRepository technicianRepository;
    private LoginService loginService;

    public LoginApiController(TechnicianRepository technicianRepository, LoginService loginService) {
        this.technicianRepository = technicianRepository;
        this.loginService = loginService;
    }


}
