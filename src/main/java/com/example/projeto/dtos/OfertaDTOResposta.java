package com.example.projeto.dtos;

import java.io.Serializable;

import com.example.projeto.models.OfertaModel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OfertaDTOResposta implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private Integer tipo;
    private Integer imovelId;
    private Integer usuarioId;
    private String descricaoImovel;
    private double valor;
    private String nomeUsuario;

    public OfertaDTOResposta() {}

    public OfertaDTOResposta(OfertaModel model) {
        this.id = model.getId();
        this.setTipo(tipo);
        this.setImovelId(model.getImovelModel().getId());
        this.setUsuarioId(model.getUserModel().getId());
        this.descricaoImovel = model.getImovelModel().getDescricao();
        this.valor = model.getValor();
        this.nomeUsuario = model.getUserModel().getNome();
    }
}
