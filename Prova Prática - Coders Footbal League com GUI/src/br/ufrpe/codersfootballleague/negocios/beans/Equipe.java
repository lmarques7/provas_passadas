/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.codersfootballleague.negocios.beans;

import java.util.HashMap;
import java.util.Map;

import br.ufrpe.codersfootballleague.exceptions.JNAException;
import br.ufrpe.codersfootballleague.exceptions.JNPEException;

import java.io.Serializable;

public class Equipe implements Serializable {
    private String nome;
    private Map<Integer, String> jogadores;

    private final int NUM_MAX_JOGADORES = 11;

    public Equipe(String nome) {
        this.nome = nome;
        jogadores = new HashMap<>();
    }

    public String getNome() {
        return nome;
    }

    public Map<Integer, String> getJogadores() {
        return jogadores;
    }

    public void adicionarJogador(String nome, int num) throws JNAException {
        if (jogadores.size() < NUM_MAX_JOGADORES) {
            if (!jogadores.containsKey(num)) {
                if (!jogadores.containsValue(nome)) {
                    jogadores.put(num, nome);
                } else {
                    throw new JNAException(nome + " já foi adicionado a equipe");
                }
            } else {
                throw new JNAException("Um jogador já possui a camisa " + num);
            }
        } else {
            throw new JNAException("Equipe atingiu o limite maximo de jogadores");
        }
    }

    public void removerJogador(String nome) throws JNPEException {
        boolean encontrado = false;
        int camisa = 0;
        for (Integer i : jogadores.keySet()) {
            if (jogadores.get(i).equals(nome)) {
                camisa = i;
                encontrado = true;
            }
        }
        if (encontrado == true) {
            jogadores.remove(camisa);
        } else if (encontrado == false) {
            throw new JNPEException("Jogador não pertence a equipe " + this.nome);
        }
    }

    public boolean equals(Object obj) {
        boolean resultado = false;
        if (obj instanceof Equipe) {
            Equipe qualquer = (Equipe) obj;
            if (qualquer.getNome().equals(this.nome)) {
                resultado = true;
            }
        }
        return resultado;
    }

    @Override
    public String toString() {
        return "" + nome;
    }

}
