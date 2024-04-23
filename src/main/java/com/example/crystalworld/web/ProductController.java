package com.example.crystalworld.web;

import com.example.crystalworld.model.dto.ProductExportDto;
import com.example.crystalworld.model.entity.Picture;
import com.example.crystalworld.model.entity.Product;
import com.example.crystalworld.model.enums.TypeEnum;
import com.example.crystalworld.service.PictureService;
import com.example.crystalworld.service.ProductService;
import com.example.crystalworld.validation.AddProductBindingModel;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

@Controller
public class ProductController {

    private final PictureService pictureService;

    private final ProductService productService;

    public ProductController(PictureService pictureService, ProductService productService) {
        this.pictureService = pictureService;
        this.productService = productService;
    }

    @GetMapping("/products/{type}")
    public String products(Model model, @PathVariable("type") String type){

        TypeEnum typeEnum = TypeEnum.valueOf(type);
        List<ProductExportDto> productExportDtoList = productService.findByTypeEnum(typeEnum);
        model.addAttribute("products", productExportDtoList);

        return "products";
    }

    @GetMapping("/currentProduct/{id}")
    public String currentProduct(Model model, @PathVariable("id") Long id){

        ProductExportDto currentProduct = productService.getProductDtoById(id);
        model.addAttribute("productId", currentProduct.getId());
        model.addAttribute("productTitle", currentProduct.getName());
        model.addAttribute("productDesc", currentProduct.getDescription());
        model.addAttribute("productPrice", currentProduct.getPrice());

        return "currentProduct";
    }


    @PostMapping("/products/addProduct")
    public String confirmAddProduct(@Valid AddProductBindingModel addProductBindingModel, BindingResult bindingResult,
                                    RedirectAttributes redirectAttributes) throws IOException {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("addProductBindingModel", addProductBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addProductBindingModel", bindingResult);
        } else {

            Picture picture = pictureService.uploadPicture(addProductBindingModel.getPicture());
            Product product = productService.addProduct(addProductBindingModel, picture);
        }

        return "redirect:/admin";
    }

    @DeleteMapping("/deleteProduct/{productId}")
    public String deleteProduct(@PathVariable ("productId") Long id){

        productService.deleteCurrentProduct(id);

        return "redirect:/shop";
    }
}
