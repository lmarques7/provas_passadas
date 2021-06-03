/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.codersfootballleague.negocios.beans;

public class EquipeInformation implements Comparable<EquipeInformation>{
    
    private String nome;
    private int pontos;
    private int saldoDeGols;
    private int golsAFavor;
    private int golsContra;

    public EquipeInformation(String nome) {
        this.nome = nome;
        this.pontos = 0;
        this.saldoDeGols = 0;
        this.golsAFavor = 0;
        this.golsContra = 0;
    }
    
    public String getNome(){
        return nome;
    }
    
    public int getPontos() {
        return pontos;
    }

    public int getSaldoDeGols() {
        return saldoDeGols;
    }

    public int getGolsAFavor() {
        return golsAFavor;
    }

    public int getGolsContra() {
        return golsContra;
    }

    public void atualizarPontos(int pontos) {
        this.pontos += pontos;
    }

    public void atualizarSaldoDeGols(int saldoDeGols) {
        this.saldoDeGols += saldoDeGols;
    }

    public void atualizarGolsAFavor(int golsAFavor) {
        this.golsAFavor += golsAFavor;
    }

    public void atualizarGolsContra(int golsContra) {
        this.golsContra += golsContra;
    }
    
    
    public boolean equals(Object obj){
        boolean resultado = false;
        if(obj instanceof EquipeInformation){
            EquipeInformation qualquer = (EquipeInformation) obj;
            if(qualquer.getNome().equals(this.nome)){
                resultado = true;
            }
        }
        return resultado;
    }
    
    
    @Override
    public int compareTo(EquipeInformation o) {
        int resultado = 0;
        if(this.pontos > o.pontos){
            resultado = -1;
        }else if(this.pontos == o.pontos){
            if(this.saldoDeGols > o.saldoDeGols){
                resultado = -1;
            }else if(this.saldoDeGols == o.saldoDeGols){
                if(this.golsAFavor > o.golsAFavor){
                    resultado = -1;
                }else if(this.golsAFavor == o.golsAFavor){
                    if(this.golsContra < o.golsContra){
                        resultado = -1;
                    }else if(this.golsContra == o.golsContra){
                        resultado = 0;
                    }else if(this.golsContra > o.golsContra){
                        resultado = 1;
                    }
                }else if(this.golsAFavor < o.golsAFavor){
                    resultado = 1;
                }
            }else if(this.saldoDeGols < o.saldoDeGols){
                resultado = 1;
            }
        }else if(this.pontos < o.pontos){
            resultado = 1;
        }
        return resultado;
    }
    
}
