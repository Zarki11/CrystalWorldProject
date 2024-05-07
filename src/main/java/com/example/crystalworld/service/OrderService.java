package com.example.crystalworld.service;

import com.example.crystalworld.validation.OrderBindingModel;

public interface OrderService {

    void addNewOrder(OrderBindingModel orderBindingModel, String username);

    void clearOrder();

    void deleteOrder(Long id);
}
