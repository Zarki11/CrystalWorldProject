package com.example.crystalworld.repository;

import com.example.crystalworld.model.entity.Product;
import com.example.crystalworld.model.enums.TypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> getByForType(TypeEnum forType);
}
