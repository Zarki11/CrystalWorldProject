package com.example.crystalworld.service;

import com.example.crystalworld.model.entity.User;
import com.example.crystalworld.validation.EditProfileBindingModel;
import com.example.crystalworld.validation.UserRegisterBindingModel;

import java.util.Optional;

public interface UserService {

    void registerUser(UserRegisterBindingModel userRegisterBindingModel);

    void init();

    User findByUsername(String username);

    void loginUser(String username);

    User findUserById(Long id);

    void editProfileData(String username, EditProfileBindingModel editProfileBindingModel);
}
