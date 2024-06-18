package com.example.projeto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.example.projeto.models.DescontoModel;
import com.example.projeto.repository.DescontoRepository;

@Service
public class DescontoService {

    @Autowired
    private DescontoRepository repository;

    public List<DescontoModel> getAll() {
        try {
            List<DescontoModel> list = repository.findAll();
            if (list == null || list.isEmpty()) {
                System.out.println("Nenhum desconto encontrado no banco de dados.");
            } else {
                System.out.println("Descontos encontrados: " + list.size());
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public DescontoModel find(Integer id) {
        Optional<DescontoModel> model = repository.findById(id);
        return model.orElse(null);
    }

    public DescontoModel insert(DescontoModel model) {
        return repository.save(model);
    }

    public DescontoModel update(DescontoModel model) {
        find(model.getId());
        return repository.save(model);
    }

    public void delete(Integer id) {
        DescontoModel model = find(id);
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            throw new DataIntegrityViolationException("Não foi possível excluir");
        }
    }

    public Page<DescontoModel> findPage(Integer pagina, Integer linhas, String ordem, String direcao) {
        PageRequest pageRequest = PageRequest.of(pagina, linhas, Direction.valueOf(direcao), ordem);
        return repository.findAll(pageRequest);
    }
}
