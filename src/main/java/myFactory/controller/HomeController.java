package myFactory.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    private String homePage() {
        return "home";
    }

    @GetMapping("/error-page")
    public String errorPage() {
        return "login-error";
    }
}
