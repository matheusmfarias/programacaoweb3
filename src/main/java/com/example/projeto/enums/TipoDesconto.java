package com.example.projeto.enums;

public enum TipoDesconto {
    VALOR(1),
    PORCENTAGEM(2);

    private int codigo;

    private TipoDesconto(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigo() {
        return codigo;
    }

    public static TipoDesconto toEnum(Integer codigo) {
        if (codigo == null) {
            return null;
        }
        for (TipoDesconto x : TipoDesconto.values()) {
            if (codigo.equals(x.getCodigo())) {
                return x;
            }
        }
        throw new IllegalArgumentException("Id inválido: " + codigo);
    }
}
