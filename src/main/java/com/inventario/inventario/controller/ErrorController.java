package com.inventario.inventario.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorController {

    @GetMapping("/404")
    public String handle404() {
        return "404";
    }

    @GetMapping("/401")
    public String handle401() {
        return "401";
    }

    @GetMapping("/500")
    public String handle500() {
        return "500";
    }
}
