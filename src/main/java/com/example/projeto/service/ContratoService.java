package com.example.projeto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.projeto.controllers.exceptions.ResourceNotFoundException;
import com.example.projeto.models.ClienteModel;
import com.example.projeto.models.ContratoModel;
import com.example.projeto.models.ImovelModel;
import com.example.projeto.models.UserModel;
import com.example.projeto.repository.ClienteRepository;
import com.example.projeto.repository.ContratoRepository;
import com.example.projeto.repository.ImovelRepository;
import com.example.projeto.repository.UserRepository;

@Service
public class ContratoService {

    @Autowired
    private ContratoRepository repository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ImovelRepository imovelRepository;

    public List<ContratoModel> getAll() {
        return repository.findAll();
    }

    public ClienteModel findClienteById(Integer id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado"));
    }

    public UserModel findUserById(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));
    }

    public ImovelModel findImovelById(Integer id) {
        return imovelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Imóvel não encontrado"));
    }

    public ContratoModel insert(ContratoModel model) {
        return repository.save(model);
    }

    public ContratoModel update(ContratoModel model) {
        if (model.getId() == null) {
            throw new IllegalArgumentException("O id do contrato não pode ser nulo.");
        }
        return repository.save(model);
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
