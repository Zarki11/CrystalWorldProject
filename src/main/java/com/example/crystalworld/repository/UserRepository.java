package com.example.crystalworld.repository;

import com.example.crystalworld.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository <User, Long>{

    User findByUsername(String username);
}
