package com.example.projeto.dtos;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

import com.example.projeto.models.ClienteModel;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class ClienteDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String nome;
    private String email;
    private String cpf;
    private List<ContratoDTOResposta> contratos;

    public ClienteDTO() {
    }

    public ClienteDTO(ClienteModel model) {
        this.id = model.getId();
        this.nome = model.getNome();
        this.email = model.getEmail();
        this.cpf = model.getCpf();
        this.contratos = model.getContratos() != null ? model.getContratos().stream()
                .map(ContratoDTOResposta::new)
                .collect(Collectors.toList()) : new ArrayList<>();
    }

    public static ClienteDTO transformaEmDTO(ClienteModel clienteModel) {
        return new ClienteDTO(clienteModel);
    }
}
