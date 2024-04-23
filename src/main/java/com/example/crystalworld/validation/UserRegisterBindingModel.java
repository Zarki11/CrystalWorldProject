package com.example.crystalworld.validation;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserRegisterBindingModel {
    private Long id;
    @Size(min = 4, max = 15, message = "Потребителското име трябва да между 4 и 15 символа!")
    private String username;
    @Size(min = 2, max = 20, message = "Името трябва да е между 2 и 20 символа!")
    private String firstName;
    @Size(min = 2, max = 20, message = "Фамилията трябва да е между 2 и 20 символа!")
    private String lastName;
    @Email(message = "Въведете валиден имейл адрес!")
    @NotBlank(message = "Въведете имейл адрес!")
    private String email;
    @Size(min = 3, max = 20, message = "Паролата трябва да между 3 и 20 символа!")
    private String password;
    @Size(min = 3, max = 20, message = "Паролата трябва да между 3 и 20 символа!")
    private String confirmPassword;

    public UserRegisterBindingModel(String username, String firstName, String lastName, String email, String password, String confirmPassword) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public UserRegisterBindingModel() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
