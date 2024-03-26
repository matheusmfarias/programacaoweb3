package com.example.projeto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.projeto.models.ClienteModel;

public interface ClienteRepository extends JpaRepository<ClienteModel, Integer> {

    
}