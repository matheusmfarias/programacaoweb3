package com.example.projeto.enums;

public enum TipoOferta {
    VENDA(1, "Venda"),
    ALUGUEL(2, "Aluguel");

    private int codigo;
    private String descricao;

    private TipoOferta(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static TipoOferta toEnum(Integer codigo) {
        if (codigo == null) {
            return null;
        }

        for (TipoOferta tipo : TipoOferta.values()) {
            if (codigo.equals(tipo.getCodigo())) {
                return tipo;
            }
        }

        throw new IllegalArgumentException("Código inválido: " + codigo);
    }
}
