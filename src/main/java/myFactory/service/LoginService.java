package myFactory.service;

import myFactory.model.dtos.LoginDTO;
import myFactory.model.entities.Technician;
import myFactory.repository.TechnicianRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
public class LoginService {
//    private TechnicianRepository technicianRepository;
//    private final PasswordEncoder passwordEncoder;
//
//    public LoginService(TechnicianRepository technicianRepository, PasswordEncoder passwordEncoder) {
//        this.technicianRepository = technicianRepository;
//        this.passwordEncoder = passwordEncoder;
//    }
//
//    public Technician getTechnicianByWorkerIdentity(String workerIdentity) {
//        return technicianRepository.getByWorkerIdentityNickname(workerIdentity).orElseThrow(() -> new UsernameNotFoundException("User not found"));
//    }
}