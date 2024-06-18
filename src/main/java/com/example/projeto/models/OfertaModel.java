package com.example.projeto.models;

import java.io.Serializable;

import com.example.projeto.enums.TipoOferta;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="ofertas")
public class OfertaModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Integer id;

    private Integer tipoOferta;

    private double valor;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="imovel_id")
    private ImovelModel imovelModel;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "usuario_id")
    private UserModel userModel;

    public TipoOferta getTipoOferta() {
        return TipoOferta.toEnum(tipoOferta);
    }

    public void setTipoOferta(TipoOferta tipoOferta) {
        this.tipoOferta = tipoOferta != null ? tipoOferta.getCodigo() : null;
    }
}
