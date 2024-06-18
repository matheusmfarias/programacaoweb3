package com.example.projeto.dtos;

import java.io.Serializable;
import java.util.Date;

import com.example.projeto.models.DescontoModel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DescontoDTOResposta implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private Date dataExpiracao;
    private String tipo;
    private double valor;
    private Integer ofertaId;

    public DescontoDTOResposta() {}

    public DescontoDTOResposta(DescontoModel model) {
        this.id = model.getId();
        this.dataExpiracao = model.getDataExpiracao();
        this.tipo = (model.getTipoDesconto() != null) ? model.getTipoDesconto().toString() : null;
        this.valor = model.getValor();
        this.ofertaId = model.getOfertaModel().getId();
    }
}
