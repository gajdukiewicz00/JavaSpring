package org.example.javaspring.bikerental.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CustomErrorController implements ErrorController {

    @GetMapping("/error")
    public String handleError() {
        return "errors/500";
    }

    @GetMapping("/403")
    public String accessDenied() {
        return "errors/403";
    }

    @GetMapping("/404")
    public String notFound() {
        return "errors/404";
    }
}
