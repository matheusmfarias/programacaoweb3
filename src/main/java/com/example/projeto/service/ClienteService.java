package com.example.projeto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.example.projeto.models.ClienteModel;
import com.example.projeto.repository.ClienteRepository;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    public List<ClienteModel> getAll() {
        try {
            List<ClienteModel> list = repository.findAll();
            return list;
        } catch (Exception e) {
            System.out.println(e.toString());
            return null;
        }
    }

    public ClienteModel find(Integer id) {
        Optional<ClienteModel> model = repository.findById(id);
        return model.orElse(null);
    }

    public ClienteModel insert(ClienteModel model) {
        return repository.save(model);
    }

    public ClienteModel update(ClienteModel model) {
        find(model.getId());
        return repository.save(model);
    }

    public void delete(Integer id) {
        ClienteModel model = find(id);
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            throw new DataIntegrityViolationException("Não foi possível exlcluir");
        }
    }

    public Page<ClienteModel> findPage(Integer pagina, Integer linhas, String ordem, String direcao) {
        PageRequest request = PageRequest.of(pagina, linhas, Direction.valueOf(direcao), ordem);
        return repository.findAll(request);
    }

}
