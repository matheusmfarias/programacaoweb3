package com.example.projeto.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.example.projeto.controllers.exceptions.ResourceNotFoundException;
import com.example.projeto.dtos.ClienteDTO;
import com.example.projeto.models.ClienteModel;
import com.example.projeto.models.ContratoModel;
import com.example.projeto.repository.ClienteRepository;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    public List<ClienteModel> getAll() {
        return repository.findAll();
    }

    public ClienteModel find(Integer id) {
        Optional<ClienteModel> model = repository.findById(id);
        return model.orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado com id " + id));
    }

    public ClienteModel insert(ClienteModel model) {
        return repository.save(model);
    }

    public ClienteModel update(ClienteModel model) {
        find(model.getId()); // Certifique-se de que o cliente existe antes de atualizar
        return repository.save(model);
    }

    public void delete(Integer id) {
        ClienteModel model = find(id);
        repository.delete(model);
    }

    public ClienteModel transformaParaObjeto(ClienteDTO clienteDTO) {
        ClienteModel cliente = new ClienteModel(
                clienteDTO.getNome(),
                clienteDTO.getEmail(),
                clienteDTO.getCpf());

        if (clienteDTO.getId() != null) {
            cliente.setId(clienteDTO.getId());
        }

        if (clienteDTO.getContratos() != null) {
            cliente.setContratos(
                    clienteDTO.getContratos().stream()
                            .map(contratoDTO -> new ContratoModel(
                                    contratoDTO.getValor(),
                                    cliente, // Aqui referenciamos o cliente para manter a relação bidirecional
                                    null, // ajuste conforme necessário para UserModel
                                    null // ajuste conforme necessário para ImovelModel
                            ))
                            .collect(Collectors.toList()));
        }
        return cliente;
    }
}
