package myFactory.controller;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @PostConstruct
    public void init() {
        System.out.println("HomeController initialized.");
    }
    @GetMapping("/")
    private String homePage() {
        return "home";
    }
}
