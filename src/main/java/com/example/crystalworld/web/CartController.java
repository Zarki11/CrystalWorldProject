package com.example.crystalworld.web;

import com.example.crystalworld.model.dto.ProductExportDto;
import com.example.crystalworld.model.entity.Product;
import com.example.crystalworld.model.entity.User;
import com.example.crystalworld.service.ProductService;
import com.example.crystalworld.service.UserService;
import com.example.crystalworld.util.Cart;
import com.example.crystalworld.validation.OrderBindingModel;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class CartController {

    private final Cart cart;

    private final ProductService productService;

    private final UserService userService;

    public CartController(Cart cart, ProductService productService, UserService userService) {
        this.cart = cart;
        this.productService = productService;
        this.userService = userService;
    }

    @GetMapping("/cart")
    public String viewCart(@AuthenticationPrincipal UserDetails userDetails, Model model){

        List<ProductExportDto> productList = cart.getProducts();
        double finalPrice = productService.sumFinalPrice(productList);

        model.addAttribute("productsList", productList);
        model.addAttribute("finalPrice", finalPrice);
        model.addAttribute("orderBindingModel", new OrderBindingModel());

        User user = userService.findByUsername(userDetails.getUsername());

        model.addAttribute("userFirstName", user.getFirstName());
        model.addAttribute("userLastName", user.getLastName());
        model.addAttribute("userEmail", user.getEmail());



        return "cart";
    }

    @GetMapping("/removeFromCart/{index}")
    public String removeFromCart(@PathVariable("index") int index) {

        ProductExportDto exportDto = cart.getProducts().remove(index);

        return "redirect:/cart";
    }
}
