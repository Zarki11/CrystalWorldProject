package com.example.crystalworld.service.impl;

import com.example.crystalworld.model.dto.ProductExportDto;
import com.example.crystalworld.model.entity.Order;
import com.example.crystalworld.model.entity.OrderedProduct;
import com.example.crystalworld.model.entity.User;
import com.example.crystalworld.repository.OrderRepository;
import com.example.crystalworld.repository.OrderedProductRepository;
import com.example.crystalworld.repository.UserRepository;
import com.example.crystalworld.service.OrderService;
import com.example.crystalworld.util.Cart;
import com.example.crystalworld.validation.OrderBindingModel;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final Cart cart;
    private final ModelMapper modelMapper;
    private final OrderedProductRepository orderedProductRepository;
    private final UserRepository userRepository;

    private final OrderRepository orderRepository;

    public OrderServiceImpl(Cart cart, ModelMapper modelMapper, OrderedProductRepository orderedProductRepository, UserRepository userRepository, OrderRepository orderRepository) {
        this.cart = cart;
        this.modelMapper = modelMapper;
        this.orderedProductRepository = orderedProductRepository;
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
    }


    @Override
    public void addNewOrder(OrderBindingModel orderBindingModel, String username) {

        List<OrderedProduct> orderedProductList = new ArrayList<>();

        for (ProductExportDto productExportDto : cart.getProducts()){
            OrderedProduct orderedProduct = modelMapper.map(productExportDto, OrderedProduct.class);
            orderedProduct.setProductId(productExportDto.getId());
            orderedProduct.setCount(1);

            orderedProductList.add(orderedProduct);
        }

        orderedProductRepository.saveAll(orderedProductList);
        User user = userRepository.findByUsername(username);

        Order order = modelMapper.map(orderBindingModel, Order.class);
        order.setUser(user);
        order.setCreatedOn(LocalDateTime.now());
        order.setOrderedProducts(orderedProductList);
        orderRepository.save(order);
        cart.getProducts().clear();
    }

    @Override
    public void clearOrder() {

        for (Order order : orderRepository.findAll()) {
            if (order.getCreatedOn().isAfter(order.getCreatedOn().plusDays(7))) {
                orderRepository.delete(order);
            }
        }
    }

    @Override
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}
