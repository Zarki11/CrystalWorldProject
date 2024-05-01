package com.example.crystalworld.service;


import com.example.crystalworld.model.dto.ProductExportDto;
import com.example.crystalworld.model.entity.Picture;
import com.example.crystalworld.model.entity.Product;
import com.example.crystalworld.model.enums.TypeEnum;
import com.example.crystalworld.validation.AddProductBindingModel;

import java.util.List;

public interface ProductService {

    Product addProduct(AddProductBindingModel addProductBindingModel, Picture picture);

    Product findProductById(Long id);

    List<ProductExportDto> findByTypeEnum(TypeEnum typeEnum);

    ProductExportDto getProductDtoById(Long id);

    void deleteCurrentProduct(Long id);

    double sumFinalPrice(List<ProductExportDto> productList);
}
