/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.codersfootballleague.dados;

import br.ufrpe.codersfootballleague.exceptions.ENAException;
import br.ufrpe.codersfootballleague.negocios.beans.Equipe;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import java.util.Collections;
import java.util.List;

public class RepositorioEquipes implements IRepositorioEquipes {

    private ArrayList<Equipe> equipes;
    private File f;
    private FileOutputStream fos;
    private FileInputStream fis;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;

    private static IRepositorioEquipes instance;

    private RepositorioEquipes() {
        f = new File("Equipes Database");
        if (!f.exists()) {
            try {
                f.createNewFile();
                equipes = new ArrayList<>();
                salvar();
            } catch (IOException e) {
                e.printStackTrace(); // Stack trace impresso, mas sem tratamento
            }
        } else {
            carregar();
        }

    }

    public static IRepositorioEquipes getInstance() {
        if (instance == null) {
            instance = new RepositorioEquipes();
        }
        return instance;
    }

    /* (non-Javadoc)
     * @see br.ufrpe.codersfootballleague.dados.IRepositorioEquipes#adicionar(br.ufrpe.codersfootballleague.negocios.beans.Equipe)
     */
    @Override
    public void adicionar(Equipe e) throws ENAException {
        if (e != null) {
            for (Equipe qualquer : equipes) {
                if (!qualquer.getNome().equals(e.getNome())) {
                    for (String jE : e.getJogadores().values()) {
                        if (qualquer.getJogadores().values().contains(jE)) {
                            throw new ENAException("Jogador " + jE + " já faz parte do time " + qualquer.getNome());
                        }
                    }
                } else {
                    throw new ENAException("Equipe com mesmo nome já existe");
                }
            }
            equipes.add(e);
            salvar();
        } else {
            throw new ENAException("Equipe não selecionada");
        }

    }

    /* (non-Javadoc)
     * @see br.ufrpe.codersfootballleague.dados.IRepositorioEquipes#listar()
     */
    @Override
    public List<Equipe> listar() {
        return Collections.unmodifiableList(equipes);
    }

    /* (non-Javadoc)
     * @see br.ufrpe.codersfootballleague.dados.IRepositorioEquipes#atualizar(br.ufrpe.codersfootballleague.negocios.beans.Equipe, java.lang.String)
     */
    @Override
    public void atualizar(Equipe nova, String antiga) throws ENAException {
        int index = 0;
        for (Equipe e : equipes) {
            if (!e.getNome().equals(antiga)) {
                for (String jE : nova.getJogadores().values()) {
                    if (e.getJogadores().values().contains(jE)) {
                        throw new ENAException("Jogador " + jE + " já faz parte do time " + e.getNome());
                    }
                }
            } else {
                index = equipes.indexOf(e);
            }
        }
        if (index != 0) {
            equipes.set(index, nova);
        }
        salvar();
    }

    /* (non-Javadoc)
     * @see br.ufrpe.codersfootballleague.dados.IRepositorioEquipes#remover(java.lang.String)
     */
    @Override
    public void remover(String nome) {
        int index = 0;
        for (Equipe e : equipes) {
            if (e.getNome().equals(nome)) {
                index = equipes.indexOf(e);
            }
        }
        equipes.remove(index);
        salvar();
    }

    private void inicializeOutStreams() {
        try {
            fos = new FileOutputStream(f, false);
            oos = new ObjectOutputStream(fos);
        } catch (IOException e) {
            e.printStackTrace(); // Stack trace impresso, mas sem tratamento
        }
    }

    private void inicializeInStreams() {
        try {
            fis = new FileInputStream(f);
            ois = new ObjectInputStream(fis);
        } catch (IOException e) {
            e.printStackTrace(); // Stack trace impresso, mas sem tratamento
        }
    }

    /* (non-Javadoc)
     * @see br.ufrpe.codersfootballleague.dados.IRepositorioEquipes#salvar()
     */
    @Override
    public void salvar() {
        try {
            inicializeOutStreams();
            oos.writeObject(equipes);
            oos.flush();
            oos.close();
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace(); // Stack trace impresso, mas sem tratamento
        }
    }

    private void carregar() {
        try {
            equipes = new ArrayList<>();
            f = new File("Equipes Database");
            inicializeInStreams();
            equipes.addAll((ArrayList<Equipe>) ois.readObject());
            ois.close();
            fis.close();

        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace(); // Stack trace impresso, mas sem tratamento
        }
    }

}
