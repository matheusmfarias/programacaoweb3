package com.example.projeto.models;

import com.example.projeto.enums.TipoDesconto;
import java.io.Serializable;
import java.util.Date;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="descontos")
public class DescontoModel implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private double valor;

    private Integer tipoDesconto;

    @Temporal(TemporalType.DATE)
    private Date dataExpiracao;

    @ManyToOne
    @JoinColumn(name = "oferta_id")
    private OfertaModel ofertaModel;


    public TipoDesconto getTipoDesconto() {
        return TipoDesconto.toEnum(tipoDesconto);
    }

    public void setTipoDesconto(TipoDesconto tipoDesconto) {
        this.tipoDesconto = tipoDesconto.getCodigo();
    }
}
