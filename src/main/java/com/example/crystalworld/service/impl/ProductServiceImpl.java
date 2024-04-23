package com.example.crystalworld.service.impl;

import com.example.crystalworld.model.dto.ProductExportDto;
import com.example.crystalworld.model.entity.Picture;
import com.example.crystalworld.model.entity.Product;
import com.example.crystalworld.model.enums.TypeEnum;
import com.example.crystalworld.repository.ProductRepository;
import com.example.crystalworld.service.ProductService;
import com.example.crystalworld.validation.AddProductBindingModel;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final ModelMapper modelMapper;

    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Product addProduct(AddProductBindingModel addProductBindingModel, Picture picture) {

        Product product = modelMapper.map(addProductBindingModel, Product.class);
        product.setForType(TypeEnum.valueOf(addProductBindingModel.getForType()));
        product.setPicture(picture);

        productRepository.save(product);

        return product;
    }


    public Product findProductById(Long id){

        return productRepository.findById(id).get();
    }

    @Override
    public List<ProductExportDto> findByTypeEnum(TypeEnum typeEnum) {

        return productRepository.getByForType(typeEnum)
                .stream()
                .map(product -> modelMapper.map(product, ProductExportDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public ProductExportDto getProductDtoById(Long id) {

        Product product = productRepository.findById(id).get();

        ProductExportDto productExportDto = modelMapper.map(product, ProductExportDto.class);
        productExportDto.setPictureId(product.getPicture().getId());
        return productExportDto;
    }

    @Override
    public void deleteCurrentProduct(Long id) {

        productRepository.deleteById(id);
    }


}
