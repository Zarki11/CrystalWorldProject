package com.example.crystalworld.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/aboutUs")
    public String aboutUs(){

        return "aboutUs";
    }

    @GetMapping("/shop")
    public String shop(){

        return "shop";
    }

    @GetMapping("/contacts")
    public String contacts(){

        return "contacts";
    }
}
