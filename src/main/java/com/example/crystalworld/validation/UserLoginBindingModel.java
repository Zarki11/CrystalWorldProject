package com.example.crystalworld.validation;

import jakarta.validation.constraints.Size;

public class UserLoginBindingModel {

    private Long id;

    @Size(min = 4, max = 15, message = "Username length must be between 4 and 15 characters!")
    private String username;

    @Size(min = 3, max = 20, message = "Password length must be between 3 and 20 characters!")
    private String password;

    public UserLoginBindingModel(){
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
