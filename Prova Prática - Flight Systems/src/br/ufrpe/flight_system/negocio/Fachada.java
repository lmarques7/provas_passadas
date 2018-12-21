package br.ufrpe.flight_system.negocio;

import java.util.List;

import br.ufrpe.flight_system.exception.AssentoInvalidoException;
import br.ufrpe.flight_system.exception.AssentoJaMarcadoException;
import br.ufrpe.flight_system.exception.ElementoJaExisteException;
import br.ufrpe.flight_system.exception.ElementoNaoExisteException;
import br.ufrpe.flight_system.negocio.beans.Bilhete;
import br.ufrpe.flight_system.negocio.beans.Cidade;
import br.ufrpe.flight_system.negocio.beans.Passageiro;
import br.ufrpe.flight_system.negocio.beans.Voo;

public class Fachada {
    
    private static Fachada instance;
    
    private ControladorBilhetes controladorBilhetes;
    private ControladorVoos controladorVoos;
    private ControladorPassageiros controladorPassageiros;
    
    private Fachada() {
        // construtor privado para implementar padrão Singleton
        this.controladorBilhetes = ControladorBilhetes.getInstance();
        this.controladorVoos = ControladorVoos.getInstance();
        this.controladorPassageiros = ControladorPassageiros.getInstance();
    }
    
    public static Fachada getInstance() {
        if (instance == null) {
            instance = new Fachada();
        }
        return instance;
    }

    public Bilhete emitirBilhete(Passageiro p, Voo v, int idAssento)
            throws ElementoJaExisteException, AssentoInvalidoException,
            AssentoJaMarcadoException {
       return controladorBilhetes.emitirBilhete(p, v, idAssento);
    }

    public List<Bilhete> listarBilhetes() {
        return controladorBilhetes.listar();
    }

    public void remover(Bilhete obj) throws ElementoNaoExisteException {
        controladorBilhetes.remover(obj);
    }

    public void atualizar(Bilhete newObj, int novoAssento) 
            throws ElementoNaoExisteException, ElementoJaExisteException {
        controladorBilhetes.atualizar(newObj, novoAssento);
    }

    public List<Bilhete> listarBilhetesPorVoo(Voo v) {        
        return controladorBilhetes.listarBilhetesPorVoo(v);
    }

    public void inserir(Voo obj) throws ElementoJaExisteException {
        controladorVoos.inserir(obj);
    }

    public void remover(Voo obj) throws ElementoNaoExisteException {
        controladorVoos.remover(obj);
    }

    public void atualizar(Voo newObj) throws ElementoNaoExisteException {
        controladorVoos.atualizar(newObj);
    }

    public List<Voo> listarVoosQuePassamPorCidade(Cidade origemOuDestino) {
        return controladorVoos.listarVoosQuePassamPorCidade(origemOuDestino);
    }

    public List<Voo> listarVoos() {
        return controladorVoos.listar();
    }

    public void inserirPassageiro(Passageiro obj) throws ElementoJaExisteException {
        controladorPassageiros.inserir(obj);
    }

    public List<Passageiro> listarPassegeiros() {
        return controladorPassageiros.listar();
    }

    public void removerPassageiro(Passageiro obj) throws ElementoNaoExisteException {
        controladorPassageiros.remover(obj);
    }

    public void atualizarPassageiro(Passageiro newObj) throws ElementoNaoExisteException {
        controladorPassageiros.atualizar(newObj);
    }
    
}
