package com.example.projeto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.example.projeto.models.UserModel;
import com.example.projeto.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public UserModel find(Integer id) {
        Optional<UserModel> obj = repository.findById(id);
        return obj.orElse(null);
    }

    public List<UserModel> getAll() {
        return repository.findAll();
    }

    public UserModel insert(UserModel model) {
        return repository.save(model);
    }

    public UserModel update(UserModel model) {
        find(model.getId());
        return repository.save(model);
    }

    public void delete(Integer id) {
        UserModel model = find(id);
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            throw new DataIntegrityViolationException("Não foi possível excluir");
        }
    }

    public Page<UserModel> findPage(Integer pagina, Integer linhas, String ordem, String direcao) {
        PageRequest request = PageRequest.of(pagina, linhas, Direction.valueOf(direcao), ordem);
        return repository.findAll(request);
    }
}
