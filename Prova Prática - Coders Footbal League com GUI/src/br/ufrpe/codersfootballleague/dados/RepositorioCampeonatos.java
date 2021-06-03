/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.codersfootballleague.dados;

import br.ufrpe.codersfootballleague.exceptions.CAEException;
import br.ufrpe.codersfootballleague.exceptions.CNEException;
import br.ufrpe.codersfootballleague.negocios.beans.Campeonato;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RepositorioCampeonatos implements IRepositorioCampeonatos {

    private ArrayList<Campeonato> campeonatos;
    private File f;
    private FileOutputStream fos;
    private FileInputStream fis;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    private static IRepositorioCampeonatos instance;

    private RepositorioCampeonatos() {
        f = new File("Campeonatos Database");
        if (!f.exists()) {
            try {
                f.createNewFile();
                campeonatos = new ArrayList<>();
                salvar();
            } catch (IOException e) {
                e.printStackTrace(); // Stack trace impresso, mas sem tratamento
            }
        } else {
            carregar();
        }

    }

    public static IRepositorioCampeonatos getInstance() {
        if (instance == null) {
            instance = new RepositorioCampeonatos();
        }
        return instance;
    }

    /* (non-Javadoc)
     * @see br.ufrpe.codersfootballleague.dados.IRepositorioCampeonatos#adicionar(br.ufrpe.codersfootballleague.negocios.beans.Campeonato)
     */
    @Override
    public void adicionar(Campeonato c) throws CAEException {
        for (Campeonato qualquer : campeonatos) {
            if (c.getNome().equals(qualquer.getNome())) {
                throw new CAEException("Campeonato com mesmo nome já existente");
            }
        }
        campeonatos.add(c);
        salvar();
    }

    /* (non-Javadoc)
     * @see br.ufrpe.codersfootballleague.dados.IRepositorioCampeonatos#consultar(java.lang.String)
     */
    @Override
    public Campeonato consultar(String nome) throws CNEException {
        for (Campeonato qualquer : campeonatos) {
            if (qualquer.getNome().equals(nome)) {
                return qualquer;
            }
        }
        throw new CNEException("Campeonato não existe");
    }

    /* (non-Javadoc)
     * @see br.ufrpe.codersfootballleague.dados.IRepositorioCampeonatos#listar()
     */
    @Override
    public List<Campeonato> listar() {
        return Collections.unmodifiableList(campeonatos);
    }

    /* (non-Javadoc)
     * @see br.ufrpe.codersfootballleague.dados.IRepositorioCampeonatos#remove(java.lang.String)
     */
    @Override
    public void remove(String nome) throws CNEException {
        for (Campeonato qualquer : campeonatos) {
            if (qualquer.getNome().equals(nome)) {
                campeonatos.remove(qualquer);
                salvar();
            }
        }
        throw new CNEException("Campeonato não existe");
    }

    private void InicializeOutStreams() {
        try {
            fos = new FileOutputStream(f, false);
            oos = new ObjectOutputStream(fos);
        } catch (IOException e) {
            e.printStackTrace(); // Stack trace impresso, mas sem tratamento
        }
    }

    private void InicializeInStreams() {
        try {
            fis = new FileInputStream(f);
            ois = new ObjectInputStream(fis);
        } catch (IOException e) {
            e.printStackTrace(); // Stack trace impresso, mas sem tratamento
        }
    }

    /* (non-Javadoc)
     * @see br.ufrpe.codersfootballleague.dados.IRepositorioCampeonatos#salvar()
     */
    @Override
    public void salvar() {
        try {
            InicializeOutStreams();
            System.out.println(campeonatos);
            oos.writeObject(campeonatos);
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
            campeonatos = new ArrayList<>();
            f = new File("Campeonatos Database");
            InicializeInStreams();
            campeonatos.addAll((ArrayList<Campeonato>) ois.readObject());
            ois.close();
            fis.close();

        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace(); // Stack trace impresso, mas sem tratamento
        }
    }

}
