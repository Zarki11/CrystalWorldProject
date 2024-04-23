package com.example.crystalworld.web;

import com.example.crystalworld.validation.AddProductBindingModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {


    @GetMapping("/admin")
    public String admin(Model model){

        if (!model.containsAttribute("addProductBindingModel")){
            model.addAttribute("addProductBindingModel", new AddProductBindingModel());
        }

        return "admin";
    }
}
