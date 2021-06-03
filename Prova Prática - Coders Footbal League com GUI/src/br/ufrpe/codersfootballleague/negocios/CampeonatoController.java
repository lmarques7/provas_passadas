/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.codersfootballleague.negocios;

import br.ufrpe.codersfootballleague.dados.IRepositorioCampeonatos;
import br.ufrpe.codersfootballleague.dados.RepositorioCampeonatos;
import br.ufrpe.codersfootballleague.exceptions.CAEException;
import br.ufrpe.codersfootballleague.exceptions.CNAException;
import br.ufrpe.codersfootballleague.exceptions.CNEException;
import br.ufrpe.codersfootballleague.negocios.beans.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class CampeonatoController {

    private IRepositorioCampeonatos repCampeonato;
    private static CampeonatoController instance;
    private final Random gerador = new Random((long) LocalDateTime.now().getSecond());

    private CampeonatoController() {
        repCampeonato = RepositorioCampeonatos.getInstance();
    }

    public static CampeonatoController getInstance() {
        if (instance == null) {
            instance = new CampeonatoController();
        }
        return instance;
    }

    public Campeonato simularCampeonato(List<Equipe> equipes, LocalDate dataInicioCampeonato, String nomeCampeonato) throws CNAException, CAEException {
        if (equipes.size() >= 4) {
            ArrayList<Partida> partidas = new ArrayList<>();
            LocalDate dataDaPartida = LocalDate.from(dataInicioCampeonato);
            Equipe deOntem1 = null;
            Equipe deOntem2 = null;
            while (partidas.size() != fat(equipes.size()) / (2 * fat(equipes.size() - 2))) {
                for (Equipe equipeDaCasa : equipes) {
                    if (equipeDaCasa.getJogadores().size() < 3) {
                        throw new CNAException("Jogadores insuficentes na equipe " + equipeDaCasa.getNome());
                    }
                    for (Equipe equipeVisitante : equipes) {
                        if (!equipeDaCasa.getNome().equals(equipeVisitante.getNome())) {
                            LocalDateTime inicio = LocalDateTime.of(dataDaPartida, LocalTime.of(20, 0));
                            short placar1 = (short) (gerador.nextInt(400) % 8);
                            short placar2 = (short) (gerador.nextInt(400) % 8);
                            Partida p = new PartidaOficial(equipeVisitante, equipeDaCasa, inicio, placar1, placar2, 5000, 100);
                            if (!partidas.contains(p) && !(equipeDaCasa.equals(deOntem1) || equipeVisitante.equals(deOntem2) || equipeDaCasa.equals(deOntem2) || equipeVisitante.equals(deOntem1))) {
                                partidas.add(p);
                                deOntem1 = equipeDaCasa;
                                deOntem2 = equipeVisitante;
                                dataDaPartida = dataDaPartida.plusDays(1);
                                //dataDaPartida.plus(1, ChronoUnit.DAYS);
                            } else if (partidas.contains(p) && !(equipeDaCasa.equals(deOntem1) || equipeVisitante.equals(deOntem2) || equipeDaCasa.equals(deOntem2) || equipeVisitante.equals(deOntem1))) {
                                dataDaPartida = dataDaPartida.plusDays(1);
                                deOntem1 = null;
                                deOntem2 = null;
                                //dataDaPartida.plus(1, ChronoUnit.DAYS);
                            }
                        }
                    }
                }
            }
            Campeonato c = new Campeonato(nomeCampeonato, partidas, dataInicioCampeonato, dataDaPartida);
            repCampeonato.adicionar(c);
            repCampeonato.salvar();
            return c;
        } else {
            throw new CNAException("Numero de equipes insuficiente");
        }
    }

    public List<EquipeInformation> calcularPontuacaoPorEquipe(Campeonato c) {
        ArrayList<EquipeInformation> tabela = new ArrayList<>();
        for (Partida p : c.getPartidas()) {
            if (!tabela.contains(new EquipeInformation(p.getEquipeDaCasa().getNome()))) {
                EquipeInformation daCasa = new EquipeInformation(p.getEquipeDaCasa().getNome());
                tabela.add(daCasa);
            }
            if (!tabela.contains(new EquipeInformation(p.getEquipeVisitante().getNome()))) {
                EquipeInformation visitante = new EquipeInformation(p.getEquipeVisitante().getNome());
                tabela.add(visitante);
            }
            for (EquipeInformation eI : tabela) {
                if (eI.getNome().equals(p.getEquipeDaCasa().getNome())) {
                    if (p.getPlacarEquipeDaCasa() > p.getPlacarEquipeVisitante()) {
                        eI.atualizarPontos(3);
                    } else if (p.getPlacarEquipeDaCasa() == p.getPlacarEquipeVisitante()) {
                        eI.atualizarPontos(1);
                    }
                    eI.atualizarSaldoDeGols(p.getPlacarEquipeDaCasa() - p.getPlacarEquipeVisitante());
                    eI.atualizarGolsAFavor(p.getPlacarEquipeDaCasa());
                    eI.atualizarGolsContra(p.getPlacarEquipeVisitante());
                } else if (eI.getNome().equals(p.getEquipeVisitante().getNome())) {
                    if (p.getPlacarEquipeVisitante() > p.getPlacarEquipeDaCasa()) {
                        eI.atualizarPontos(3);
                    } else if (p.getPlacarEquipeVisitante() == p.getPlacarEquipeDaCasa()) {
                        eI.atualizarPontos(1);
                    }
                    eI.atualizarSaldoDeGols(p.getPlacarEquipeVisitante() - p.getPlacarEquipeDaCasa());
                    eI.atualizarGolsAFavor(p.getPlacarEquipeVisitante());
                    eI.atualizarGolsContra(p.getPlacarEquipeDaCasa());
                }
            }
        }
        Collections.sort(tabela);
        return tabela;
    }

    public List<Partida> partidasOrdenadasPorData(Campeonato c) {
        List<Partida> partidas = c.getPartidas();
        Collections.sort(partidas, new ComparatorPartidaData());
        return partidas;
    }

    public List<Campeonato> listar() {
        return repCampeonato.listar();
    }

    public Campeonato consultar(String nome) throws CNEException {
        return repCampeonato.consultar(nome);
    }

    private int fat(int n) {
        int resultado = 1;
        for (int i = n; i > 1; i--) {
            resultado *= i;
        }
        return resultado;
    }

    public void salvar() {
        repCampeonato.salvar();
    }

}
