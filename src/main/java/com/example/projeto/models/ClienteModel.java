package com.example.projeto.models;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "clientes")
public class ClienteModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    private String email;

    private String cpf;

    @OneToMany(mappedBy = "clienteModel", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("cliente-contrato")
    private List<ContratoModel> contratos = new ArrayList<>();

    public ClienteModel(String nome, String email, String cpf) {
        super();
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
    }
}

