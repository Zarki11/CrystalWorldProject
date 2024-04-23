package com.example.crystalworld.validation;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class EditProfileBindingModel {

    @Size(min = 4, max = 15, message = "Потребителското име трябва да е с дължина между 4 и 15 символа!")
    private String username;
    @Size(min = 2, max = 20, message = "Името трябва да е с дължина между 2 и 20 символа!")
    private String firstName;
    @Size(min = 2, max = 20, message = "Фамилията трябва да е с дължина между 2 и 20 символа!")
    private String lastName;
    @Email(message = "Въведете валиден имейл адрес!")
    @NotBlank(message = "Въведете имейл адрес!")
    private String email;
    @Size(min = 3, max = 20, message = "Паролата трябва да е с дължина между 3 и 20 символа!")
    private String password;

    public EditProfileBindingModel() {
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
}
