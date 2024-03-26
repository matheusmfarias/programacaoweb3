package com.example.projeto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.projeto.models.ContratoModel;

public interface ContratoRepository extends JpaRepository<ContratoModel, Integer> {

    
}