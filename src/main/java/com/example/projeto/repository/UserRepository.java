package com.example.projeto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.projeto.models.UserModel;

public interface UserRepository extends JpaRepository<UserModel, Integer> {
UserDetails findByEmail(String email);
    
}