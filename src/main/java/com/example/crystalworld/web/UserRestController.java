package com.example.crystalworld.web;

import com.example.crystalworld.model.entity.User;
import com.example.crystalworld.service.UserService;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserRestController {

    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/profileInfo/{id}")
    public HttpEntity<List<String>> getProfileInfo(@PathVariable("id") Long id) {

        User user = userService.findUserById(id);
        List<String> result = new ArrayList<>();

        result.add(user.getUsername());
        result.add(user.getFirstName());
        result.add(user.getLastName());
        result.add(user.getEmail());


        return new HttpEntity<>(result);

    }
}
