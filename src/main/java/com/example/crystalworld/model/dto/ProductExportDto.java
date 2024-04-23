package com.example.crystalworld.model.dto;

import java.math.BigDecimal;

public class ProductExportDto {

    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private int count;
    private Long pictureId;

    public ProductExportDto(String name, String description, BigDecimal price, int count) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.count = count;
    }

    public ProductExportDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Long getPictureId() {
        return pictureId;
    }

    public void setPictureId(Long pictureId) {
        this.pictureId = pictureId;
    }
}
