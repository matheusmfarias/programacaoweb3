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
@DiscriminatorValue("2")
public class ContratoVendaModel extends ContratoModel{

    private Date dataAssinatura;
    

    public ContratoVendaModel(UserModel userModel, ClienteModel clienteModel, ImovelModel imovelModel, double valor, Date dataAssinatura2) {
        super(valor, clienteModel, userModel, imovelModel);
        this.setDataAssinatura(dataAssinatura2);
    }

  
}