package com.example.projeto.dtos;

import java.io.Serializable;

import com.example.projeto.models.ClienteModel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteDTOResposta implements Serializable{
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String nome;
    private String email;
    private String cpf;

    public ClienteDTOResposta() {
    }

    public ClienteDTOResposta(ClienteModel model) {
        this.id = model.getId();
        this.nome = model.getNome();
        this.email = model.getEmail();
        this.cpf = model.getCpf();
    }

    public static ClienteDTOResposta transformaEmDTO(ClienteModel model) {
        return new ClienteDTOResposta(model);
    }
    
}
