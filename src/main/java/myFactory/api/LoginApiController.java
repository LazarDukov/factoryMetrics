package myFactory.api;

import myFactory.model.dtos.LoginDTO;
import myFactory.model.entities.Technician;
import myFactory.repository.TechnicianRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class LoginApiController {
    private TechnicianRepository technicianRepository;

    public LoginApiController(TechnicianRepository technicianRepository) {
        this.technicianRepository = technicianRepository;

    }
//    @PostMapping("/login")
//    public String postLoginPage() {
//        return "Welcome Lazar";
//    }

}
