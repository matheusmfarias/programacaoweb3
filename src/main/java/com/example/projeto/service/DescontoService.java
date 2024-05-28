package com.example.projeto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.projeto.models.DescontoModel;
import com.example.projeto.repository.DescontoRepository;

@Service
public class DescontoService {
    @Autowired
    private DescontoRepository repository;

     public List<DescontoModel> getAll() {
        List<DescontoModel> list = repository.findAll();
        return list;
    }

     public DescontoModel save(DescontoModel model) {
        return repository.save(model);
    }

    


    
}
