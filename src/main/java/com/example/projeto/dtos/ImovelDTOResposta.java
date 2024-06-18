package com.example.projeto.dtos;

import java.io.Serializable;
import java.util.List;

import com.example.projeto.models.ImovelModel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImovelDTOResposta implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String descricao;
    private Integer quartos;
    private Integer vagas;
    private Integer usuarioId;
    private String nomeUsuario;
    private String imagem;
    private List<OfertaDTOResposta> ofertas;

    public ImovelDTOResposta() {}

    public ImovelDTOResposta(ImovelModel model) {
        this.setUsuarioId(model.getUserModel().getId());
        this.id = model.getId();
        this.descricao = model.getDescricao();
        this.quartos = model.getQuartos();
        this.vagas = model.getVagas();
        this.imagem = model.getImagem();
        this.nomeUsuario = model.getUserModel().getNome();
    }

    public static ImovelDTOResposta transformaEmDTO(ImovelModel model) {
        return new ImovelDTOResposta(model);
    }
}
