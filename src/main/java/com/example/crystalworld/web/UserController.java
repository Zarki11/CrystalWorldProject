package com.example.crystalworld.web;

import com.example.crystalworld.model.entity.User;
import com.example.crystalworld.service.UserService;
import com.example.crystalworld.validation.EditProfileBindingModel;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @ModelAttribute
    public EditProfileBindingModel editProfileBindingModel(){

        return new EditProfileBindingModel();
    }


    @GetMapping()
    public String index(){

        return "index";
    }

    @GetMapping("/profile")
    public String myProfile(@AuthenticationPrincipal UserDetails userDetails, Model model){

        User user = userService.findByUsername(userDetails.getUsername());
        model.addAttribute("profileId", user.getId());

        return "profile";
    }

    @GetMapping("/editProfile")
    public String editCurrentProfile() {

        return "editProfile";
    }

    @PutMapping("/confirmEditProfile")
    public String confirmEditProfile(@AuthenticationPrincipal UserDetails userDetails, @Valid EditProfileBindingModel editProfileBindingModel,
                                     BindingResult bindingResult, RedirectAttributes redirectAttributes, HttpSession httpSession){

        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("editProfileBindingModel", editProfileBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.editProfileBindingModel", bindingResult);

            return "redirect:/users/editProfile";
        }

        userService.editProfileData(userDetails.getUsername(), editProfileBindingModel);
        SecurityContextHolder.clearContext();
        httpSession.invalidate();

        return "redirect:users/profile";
    }
}
