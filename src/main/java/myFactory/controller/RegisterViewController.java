package myFactory.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/user")
public class RegisterViewController {

    @GetMapping("/register")
    public String getRegisterPage() {
        return "register";
    }


}