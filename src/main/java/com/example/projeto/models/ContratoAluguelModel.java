package com.example.projeto.models;

import java.util.Date;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("1")
public class ContratoAluguelModel extends ContratoModel {
    private Date dataInicio;

    private Date dataFim;
  
    private String indiceReajuste;
}