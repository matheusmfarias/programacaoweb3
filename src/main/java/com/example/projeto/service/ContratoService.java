package com.example.projeto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.example.projeto.models.ContratoModel;
import com.example.projeto.repository.ContratoRepository;

@Service
public class ContratoService {

    @Autowired
    private ContratoRepository repository;

    public List<ContratoModel> getAll() {
        try {
            List<ContratoModel> list = repository.findAll();
            return list;
        } catch (Exception e) {
            System.out.println(e.toString());
            return null;
        }
    }

    public ContratoModel find(Integer id) {
        Optional<ContratoModel> model = repository.findById(id);
        return model.orElse(null);
    }

    public ContratoModel insert(ContratoModel model) {
        return repository.save(model);
    }

    public ContratoModel update(ContratoModel model) {
        find(model.getId());
        return repository.save(model);
    }

    public void delete(Integer id) {
        ContratoModel model = find(id);
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            throw new DataIntegrityViolationException("Não foi possível exlcluir");
        }
    }

    public Page<ContratoModel> findPage(Integer pagina, Integer linhas, String ordem, String direcao) {
        PageRequest request = PageRequest.of(pagina, linhas, Direction.valueOf(direcao), ordem);
        return repository.findAll(request);
    }

}
