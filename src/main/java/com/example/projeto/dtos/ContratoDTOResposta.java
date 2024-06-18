package com.example.projeto.dtos;

import java.util.Date;

import com.example.projeto.models.ContratoAluguelModel;
import com.example.projeto.models.ContratoModel;
import com.example.projeto.models.ContratoVendaModel;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContratoDTOResposta {
    private Integer id;
    private double valor;
    private Integer clienteId;
    private Integer usuarioId;
    private Integer imovelId;
    private String nomeCliente;
    private String descricaoImovel;
    private String nomeUsuario;
    private Integer tipo;

    @Temporal(TemporalType.DATE)
    private Date dataAssinatura;

    @Temporal(TemporalType.DATE)
    private Date dataInicio;

    @Temporal(TemporalType.DATE)
    private Date dataFim;
    
    private String indiceReajuste;

    public ContratoDTOResposta(ContratoModel model){
        this.setId(model.getId());
        this.setValor(model.getValor());
        this.setClienteId(model.getClienteModel().getId());
        this.setUsuarioId(model.getUserModel().getId());
        this.setImovelId(model.getImovelModel().getId());
        this.nomeCliente = model.getClienteModel().getNome();
        this.descricaoImovel= model.getUserModel().getNome();
        this.nomeUsuario = model.getImovelModel().getDescricao();

        if (model instanceof ContratoAluguelModel) {
            ContratoAluguelModel contratoAluguel = (ContratoAluguelModel) model;
            this.setTipo(1);

            this.setDataInicio(contratoAluguel.getDataInicio());
            this.setDataFim(contratoAluguel.getDataFim());
            this.setIndiceReajuste(contratoAluguel.getIndiceReajuste());
        }

        if (model instanceof ContratoVendaModel) {
            ContratoVendaModel contratoVenda = (ContratoVendaModel) model;
            this.setTipo(2);
            
            this.setDataAssinatura(contratoVenda.getDataAssinatura());
        }
    }

}
