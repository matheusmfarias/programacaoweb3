package com.example.projeto.dtos;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import com.example.projeto.models.ClienteModel;

import lombok.Getter;

@Getter
public class ClienteRespostaDTO implements Serializable{
    private static final long serialVersionUID =1L;

    private Integer id;

    private String nome;

    private String email;

    private String cpf;

    private List<ContratoDTOResposta> contratos;

    public ClienteRespostaDTO(ClienteModel clienteModel){
        this.id= clienteModel.getId();
        this.nome = clienteModel.getNome();
        this.email = clienteModel.getEmail();
        this.cpf=clienteModel.getCpf();
        
        this.contratos = clienteModel.getContratos().stream()
        .map(contrato -> new ContratoDTOResposta(contrato))
        .collect(Collectors.toList());
        
    }

        
}

