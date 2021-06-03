/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.codersfootballleague.negocios.beans;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public class Campeonato implements Serializable {
    private String nome;
    private List<Partida> partidas;
    private LocalDate dataInicio;
    private LocalDate dataFim;

    public Campeonato(String nome, List<Partida> partidas, LocalDate dataInicio, LocalDate dataFim) {
        this.nome = nome;
        this.partidas = partidas;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }

    public String getNome() {
        return nome;
    }

    public List<Partida> getPartidas() {
        return partidas;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public double calcularRenda() {
        double renda = 0;
        for (Partida p : partidas) {
            renda += p.calcularRenda();
        }
        return renda;
    }

    @Override
    public String toString() {
        return "" + nome;
    }
}
