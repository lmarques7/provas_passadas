package br.ufrpe.codersfootballleague.dados;

import java.util.List;

import br.ufrpe.codersfootballleague.exceptions.CAEException;
import br.ufrpe.codersfootballleague.exceptions.CNEException;
import br.ufrpe.codersfootballleague.negocios.beans.Campeonato;

public interface IRepositorioCampeonatos {

    void adicionar(Campeonato c) throws CAEException;

    Campeonato consultar(String nome) throws CNEException;

    List<Campeonato> listar();

    void remove(String nome) throws CNEException;

    void salvar();

}