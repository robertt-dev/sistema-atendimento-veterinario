package com.veterinario.projeto.model.Enum;

public enum Patente {
    SD("SD"),
    CB("CB"),
    TERCEIRO_SGT("3º Sgt"),
    SEGUNDO_SGT("2º Sgt"),
    PRIMEIRO_SGT("1º Sgt"),
    ST("ST"),
    ASP("Asp"),
    SEGUNDO_TEN("2º Ten"),
    PRIMEIRO_TEN("1º Ten"),
    CAP("Cap"),
    MAJ("Maj"),
    TC("TC"),
    CEL("Cel"),
    GEN_BDA("Gen Bda"),
    GEN_DIV("Gen Div"),
    GEN_EX("Gen ex");

    private final String label;

    Patente(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    @Override
    public String toString() {
        return label;
    }
}


