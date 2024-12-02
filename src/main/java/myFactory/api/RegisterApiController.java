package myFactory.api;

import jakarta.validation.Valid;
import myFactory.model.dtos.ColleagueRegistrationDTO;
import myFactory.service.RegisterService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class RegisterApiController {
    private RegisterService registerService;

    public RegisterApiController(RegisterService registerService) {
        this.registerService = registerService;
    }

    @PostMapping("/register")
    public ResponseEntity<ColleagueRegistrationDTO> postRegisterPage(@Valid @ModelAttribute ColleagueRegistrationDTO colleagueRegistrationDTO, BindingResult bindingResult) {
        registerService.RegisterNewColleague(colleagueRegistrationDTO);
        return ResponseEntity.ok(colleagueRegistrationDTO);

    }
}
