package com.refanzzzz.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AppController {
//implements ErrorController
    private static final String PATH = "/error";

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("homeActive", "true");
        return "index";
    }

    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("aboutActive", "true");
        return "about";
    }

//    @RequestMapping(value = PATH)
//    public String error(Model model) {
//        model.addAttribute("homeActive", "true");
//        return "index";
//    }

//    @Override
//    public String getErrorPath() {
//        return PATH;
//    }
}
