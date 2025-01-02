package myFactory.api;

import jakarta.validation.Valid;
import myFactory.model.dtos.ColleagueRegistrationDTO;
import myFactory.service.RegisterService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/user")
public class RegisterApiController {
    private RegisterService registerService;

    public RegisterApiController(RegisterService registerService) {
        this.registerService = registerService;
    }


    @PostMapping("/register")
    public ResponseEntity<String> postRegisterPage(@Valid @ModelAttribute ColleagueRegistrationDTO colleagueRegistrationDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(error -> {
                System.out.println("Error: " + error.getDefaultMessage());
            });
            return ResponseEntity.badRequest().body("Validation errors occurred!");
        }
        registerService.RegisterNewColleague(colleagueRegistrationDTO);
        return ResponseEntity.ok("New colleague is successfully registered!");


    }
}
