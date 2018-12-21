package br.ufrpe.flight_system.dados;

import java.util.List;

import br.ufrpe.flight_system.exception.ElementoJaExisteException;
import br.ufrpe.flight_system.exception.ElementoNaoExisteException;

public interface IRepositorioGenerico<T> {
    
    void inserir(T obj) throws ElementoJaExisteException;
    
    List<T> listar();
    
    void remover(T obj) throws ElementoNaoExisteException;

    void atualizar(T newObj) throws ElementoNaoExisteException;

}
