package br.ufrpe.flight_system.negocio.beans;

import java.io.Serializable;

public enum Cidade implements Serializable {

    RECIFE                 ("Recife"), 
    SAO_PAULO              ("São Paulo"),
    SALVADOR               ("Salvador"),
    PORTO_ALEGRE           ("Porto Alegre"),
    MANAUS                 ("Manaus"),
    NATAL                  ("Natal"),
    FORTALEZA              ("Fortaleza"),
    CURITIBA               ("Curitiba"),
    BELO_HORIZONTE         ("Belo Horizonte"),
    CUIABA                 ("Cuiabá"),
    RIO_DE_JANEIRO         ("Rio de Janeiro");
    
    private String nome;
    
    Cidade (String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
    
    @Override
    public String toString() {
        return this.nome;
    }
}
