package myFactory.controller;

import myFactory.model.dtos.ColleagueRegistrationDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class RegisterController {
    @ModelAttribute("registerDTO")
    public ColleagueRegistrationDTO initNewColleague() {
        return new ColleagueRegistrationDTO();
    }

    @GetMapping("/register")
    public String getRegisterPage() {
        return "register";
    }
}
