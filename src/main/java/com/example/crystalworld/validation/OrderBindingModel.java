package com.example.crystalworld.validation;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public class OrderBindingModel {

    @Size(min = 2, max = 20, message = "Името трябва да е с дължина между 2 и 20 символа!")
    private String firstName;

    @Size(min = 2, max = 20, message = "Фамилията трябва да е с дължина между 2 и 20 символа!")
    private String lastName;

    @Size(min = 2, max = 20, message = "Името на града трябва да е с дължина между 2 и 20 символа!")
    private String town;

    private String address;

    @Size(min=10, max=10)
    private String phoneNumber;

    @Email
    private String email;

    public OrderBindingModel() {
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

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
