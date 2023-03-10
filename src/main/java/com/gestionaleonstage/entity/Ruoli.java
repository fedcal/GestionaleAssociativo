package com.gestionaleonstage.entity;

public class Ruoli {
    private int id;
    private String nomeRuolo;

    public Ruoli(){}

    public Ruoli(int id, String nomeRuolo) {
        this.id = id;
        this.nomeRuolo = nomeRuolo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeRuolo() {
        return nomeRuolo;
    }

    public void setNomeRuolo(String nomeRuolo) {
        this.nomeRuolo = nomeRuolo;
    }
}
