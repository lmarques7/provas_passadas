/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.codersfootballleague.negocios.beans;

import java.time.LocalDateTime;

public class PartidaOficial extends Partida {

    private int qtdPagantes;
    private double precoIngresso;

    public PartidaOficial(Equipe equipeVisitante, Equipe equipeDaCasa, LocalDateTime dataHoraInicio, short placarEquipeVisitante, short placarEquipeDaCasa, int qtdPagantes, double precoIngresso) {
        super(equipeVisitante, equipeDaCasa, dataHoraInicio, placarEquipeVisitante, placarEquipeDaCasa);
        this.qtdPagantes = qtdPagantes;
        this.precoIngresso = precoIngresso;
    }

    public int getQtdPagantes() {
        return qtdPagantes;
    }

    public double getPrecoIngresso() {
        return precoIngresso;
    }

    @Override
    public double calcularRenda() {
        return qtdPagantes * precoIngresso;
    }

}
