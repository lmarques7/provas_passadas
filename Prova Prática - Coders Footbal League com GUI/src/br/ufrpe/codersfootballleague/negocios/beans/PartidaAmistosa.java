/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.codersfootballleague.negocios.beans;

import java.time.LocalDateTime;

public class PartidaAmistosa extends Partida {
    private String descricaoFinalidadePartida;

    public PartidaAmistosa(Equipe equipeVisitante, Equipe equipeDaCasa, LocalDateTime dataHoraInicio, short placarEquipeVisitante, short placarEquipeDaCasa, String descricaoFinalidadePartida) {
        super(equipeVisitante, equipeDaCasa, dataHoraInicio, placarEquipeVisitante, placarEquipeDaCasa);
        this.descricaoFinalidadePartida = descricaoFinalidadePartida;
    }

    public String getDescricaoFinalidadePartida() {
        return descricaoFinalidadePartida;
    }

    @Override
    public double calcularRenda() {
        return 0;
    }

}
