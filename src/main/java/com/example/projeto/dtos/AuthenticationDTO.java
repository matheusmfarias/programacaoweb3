package com.example.projeto.dtos;

import com.example.projeto.enums.UserRole;

import jakarta.validation.constraints.NotNull;

public record AuthenticationDTO(String email, String password) {}
    

