package br.ufrpe.codersfootballleague.dados;

import java.util.List;

import br.ufrpe.codersfootballleague.exceptions.ENAException;
import br.ufrpe.codersfootballleague.negocios.beans.Equipe;

public interface IRepositorioEquipes {

    void adicionar(Equipe e) throws ENAException;

    List<Equipe> listar();

    void atualizar(Equipe nova, String antiga) throws ENAException;

    void remover(String nome);

    void salvar();

}