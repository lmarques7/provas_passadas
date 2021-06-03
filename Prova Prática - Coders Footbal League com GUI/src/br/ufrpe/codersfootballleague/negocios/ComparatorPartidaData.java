/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.codersfootballleague.negocios;

import br.ufrpe.codersfootballleague.negocios.beans.Partida;

import java.util.Comparator;

public class ComparatorPartidaData implements Comparator<Partida> {

    @Override
    public int compare(Partida o1, Partida o2) {
        return o1.getDataHoraInicio().compareTo(o2.getDataHoraInicio());
    }

}
