package com.example.projeto.dtos;

import com.example.projeto.enums.UserRole;

import jakarta.validation.constraints.NotNull;

public record RegisterDTO(@NotNull String nome, @NotNull String email,@NotNull String password, @NotNull UserRole role ) {}

