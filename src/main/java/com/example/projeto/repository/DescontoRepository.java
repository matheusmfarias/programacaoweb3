package com.example.projeto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.projeto.models.DescontoModel;

@Repository
public interface DescontoRepository extends JpaRepository<DescontoModel, Integer> {
    // Métodos personalizados de consulta podem ser adicionados aqui
}
