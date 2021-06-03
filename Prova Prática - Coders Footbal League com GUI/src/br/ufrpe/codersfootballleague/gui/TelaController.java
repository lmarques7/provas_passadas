/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.codersfootballleague.gui;

import br.ufrpe.codersfootballleague.exceptions.*;
import br.ufrpe.codersfootballleague.negocios.CampeonatoController;
import br.ufrpe.codersfootballleague.negocios.EquipeController;
import br.ufrpe.codersfootballleague.negocios.beans.Campeonato;
import br.ufrpe.codersfootballleague.negocios.beans.Equipe;
import br.ufrpe.codersfootballleague.negocios.beans.EquipeInformation;
import br.ufrpe.codersfootballleague.negocios.beans.Partida;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class TelaController implements Initializable, EventHandler<Event> {

    EquipeController eControl = EquipeController.getInstance();
    CampeonatoController cControl = CampeonatoController.getInstance();

    @FXML
    private Button btnAdicionarEquipe;
    @FXML
    private Button btnAtualizarEquipe;
    @FXML
    private Button btnRemoverEquipe;
    @FXML
    private Button btnAdicionarJogador;
    @FXML
    private Button btnRemoverJogador;

    @FXML
    private TableView<Equipe> equipes;
    @FXML
    private TableColumn<Equipe, String> nomesEquipes;

    @FXML
    private TableView<Map.Entry<Integer, String>> jogadores;
    @FXML
    private TableColumn<Map.Entry<Integer, String>, String> nomesJogadores;
    @FXML
    private TableColumn<Map.Entry<Integer, String>, Integer> camisaJogadores;

    @FXML
    private TextField nomeEquipe;
    @FXML
    private TextField nomeJogador;
    @FXML
    private TextField camisaJogador;

    private Equipe e;
    private ObservableList<Map.Entry<Integer, String>> jogadoresDaEquipe;

    @FXML
    private TextField nomeCampeonato;
    @FXML
    private DatePicker dataInicioCampeonato;
    @FXML
    private Button btnSimularCampeonato;
    @FXML
    private Button btnAdicionarEquipeCampeonato;
    @FXML
    private Button btnRemoverEquipeCampeonato;
    @FXML
    private TableView<Equipe> equipesParticipantes;
    @FXML
    private TableColumn<Equipe, String> nomesEquipesParticipantes;

    @FXML
    private TableView<Equipe> equipesLivres;
    @FXML
    private TableColumn<Equipe, String> nomesEquipesLivres;

    private List<Equipe> equipesLivresLista = new ArrayList<>();
    private List<Equipe> equipesEscolhidas = new ArrayList<>();

    @FXML
    private TableView<Partida> partidas;
    @FXML
    private TableColumn<Partida, Equipe> equipeDaCasa;
    @FXML
    private TableColumn<Partida, Equipe> equipeVisitante;
    @FXML
    private TableColumn<Partida, Short> placarDaCasa;
    @FXML
    private TableColumn<Partida, Short> placarVisitante;
    @FXML
    private TableColumn<Partida, LocalDateTime> data;

    @FXML
    private Button btnConsultar;
    @FXML
    private TableView<Campeonato> campeonatos;
    @FXML
    private TableColumn<Campeonato, String> nomesCampeonatos;

    @FXML
    private Button btnConsultar1;
    @FXML
    private TableView<Campeonato> campeonatos1;
    @FXML
    private TableColumn<Campeonato, String> nomesCampeonatos1;

    @FXML
    private TableView<EquipeInformation> tabela;
    @FXML
    private TableColumn<EquipeInformation, String> nomeTabela;
    @FXML
    private TableColumn<EquipeInformation, Integer> pontos;
    @FXML
    private TableColumn<EquipeInformation, Integer> saldoGols;
    @FXML
    private TableColumn<EquipeInformation, Integer> golsAFavor;
    @FXML
    private TableColumn<EquipeInformation, Integer> golsContra;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nomesJogadores.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Map.Entry<Integer, String>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Map.Entry<Integer, String>, String> p) {
                return new SimpleStringProperty(p.getValue().getValue());
            }
        });
        //nomesJogadores.setCellValueFactory(new MapValueFactory<>(jogadoresDaEquipe));
        camisaJogadores.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Map.Entry<Integer, String>, Integer>, ObservableValue<Integer>>() {
            @Override
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Map.Entry<Integer, String>, Integer> p) {
                return new SimpleObjectProperty<>(p.getValue().getKey());
            }
        });

        if (e != null) {
            jogadoresDaEquipe = FXCollections.observableArrayList(e.getJogadores().entrySet());
            jogadores.setItems(jogadoresDaEquipe);
        }

        nomesEquipes.setCellValueFactory(new PropertyValueFactory<>("nome"));
        equipes.setItems(FXCollections.observableList(eControl.listar()));
        equipes.setOnMouseClicked(event -> {
            this.e = equipes.getSelectionModel().getSelectedItem();
            this.atualizarTabelaJogadores();
        });

        equipesLivresLista.addAll(eControl.listar());

        nomesEquipesLivres.setCellValueFactory(new PropertyValueFactory<>("nome"));
        equipesLivres.setItems(FXCollections.observableList(eControl.listar()));

        nomesEquipesParticipantes.setCellValueFactory(new PropertyValueFactory<>("nome"));

        equipeDaCasa.setCellValueFactory(new PropertyValueFactory<>("equipeDaCasa"));
        equipeVisitante.setCellValueFactory(new PropertyValueFactory<>("equipeVisitante"));
        placarDaCasa.setCellValueFactory(new PropertyValueFactory<>("placarEquipeDaCasa"));
        placarVisitante.setCellValueFactory(new PropertyValueFactory<>("placarEquipeVisitante"));
        data.setCellValueFactory(new PropertyValueFactory<>("dataHoraInicio"));

        nomesCampeonatos.setCellValueFactory(new PropertyValueFactory<>("nome"));
        campeonatos.setItems(FXCollections.observableList(cControl.listar()));
        nomesCampeonatos1.setCellValueFactory(new PropertyValueFactory<>("nome"));
        campeonatos1.setItems(FXCollections.observableList(cControl.listar()));

        nomeTabela.setCellValueFactory(new PropertyValueFactory<>("nome"));
        pontos.setCellValueFactory(new PropertyValueFactory<>("pontos"));
        saldoGols.setCellValueFactory(new PropertyValueFactory<>("saldoDeGols"));
        golsAFavor.setCellValueFactory(new PropertyValueFactory<>("golsAFavor"));
        golsContra.setCellValueFactory(new PropertyValueFactory<>("golsContra"));

    }

    @Override
    public void handle(Event event) {
        if (event.getSource().equals(btnAdicionarEquipe)) {
            adicionarEquipe();
        } else if (event.getSource().equals(btnAtualizarEquipe)) {
            atualizarEquipe();
        } else if (event.getSource().equals(btnRemoverEquipe)) {
            removerEquipe();
        } else if (event.getSource().equals(btnAdicionarJogador)) {
            adicionarJogador();
        } else if (event.getSource().equals(btnRemoverJogador)) {
            removerJogador();
        } else if (event.getSource().equals(btnSimularCampeonato)) {
            simularCampeonato();
        } else if (event.getSource().equals(btnAdicionarEquipeCampeonato)) {
            adicionarEquipeCampeonato();
        } else if (event.getSource().equals(btnRemoverEquipeCampeonato)) {
            removerEquipeCampeonato();
        } else if (event.getSource().equals(btnConsultar)) {
            ;
            partidas.setItems(FXCollections.observableList(cControl.partidasOrdenadasPorData(campeonatos.getSelectionModel().getSelectedItem())));
        } else if (event.getSource().equals(btnConsultar1)) {
            tabela.setItems(FXCollections.observableList(cControl.calcularPontuacaoPorEquipe(campeonatos1.getSelectionModel().getSelectedItem())));
        }

    }

    private void adicionarEquipe() {
        if (!nomeEquipe.getText().isEmpty()) {
            if (e == null) {
                e = new Equipe(nomeEquipe.getText());
            }
            try {
                eControl.adicionar(e);
                equipes.setItems(FXCollections.observableList(eControl.listar()));
                equipesLivresLista.add(e);
                e = null;
                nomeEquipe.clear();
                nomeJogador.clear();
                camisaJogador.clear();
                jogadoresDaEquipe = FXCollections.observableArrayList(new ArrayList<>());
                jogadores.setItems(jogadoresDaEquipe);
                equipesLivres.setItems(FXCollections.observableList(equipesLivresLista));
            } catch (ENAException ex) {
                e = null;
                jogadoresDaEquipe = FXCollections.observableArrayList(new ArrayList<>());
                jogadores.setItems(jogadoresDaEquipe);
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setContentText(ex.getMessage());
                a.show();
            }
        } else {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("Nome invalido");
            a.show();
        }

    }

    private void atualizarEquipe() {
        if (!nomeEquipe.getText().isEmpty() && !(equipes.getSelectionModel().getSelectedItem() == null)) {
            if (e == null) {
                e = new Equipe(nomeEquipe.getText());
            }
            try {
                eControl.atualizar(e, equipes.getSelectionModel().getSelectedItem().getNome());
                equipesLivresLista.add(e);
                e = null;
                nomeEquipe.clear();
                nomeJogador.clear();
                camisaJogador.clear();
                jogadoresDaEquipe = FXCollections.observableArrayList(new ArrayList<>());
                jogadores.setItems(jogadoresDaEquipe);
                equipes.setItems(FXCollections.observableList(eControl.listar()));
                equipesLivres.setItems(FXCollections.observableList(equipesLivresLista));
            } catch (ENAException ex) {
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setContentText(ex.getMessage());
                a.show();
            }
        } else {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("Nome invalido ou Equipe não selecionada");
            a.show();
        }

    }

    private void removerEquipe() {
        if (!(equipes.getSelectionModel().getSelectedItem() == null)) {
            eControl.remove(equipes.getSelectionModel().getSelectedItem().getNome());
            equipes.setItems(FXCollections.observableList(eControl.listar()));
            equipesLivresLista.remove(equipes.getSelectionModel().getSelectedItem());
            equipesLivres.setItems(FXCollections.observableList(equipesLivresLista));
        } else {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("Equipe não selecionada");
            a.show();
        }

    }

    private void adicionarJogador() {
        if (!nomeJogador.getText().isEmpty() && !camisaJogador.getText().isEmpty()) {
            if (e != null || !nomeEquipe.getText().isEmpty()) {
                if (e == null) {
                    e = new Equipe(nomeEquipe.getText());
                }
                try {
                    e.adicionarJogador(nomeJogador.getText(), Integer.parseInt(camisaJogador.getText()));
                    this.atualizarTabelaJogadores();
                } catch (JNAException ex) {
                    Alert a = new Alert(Alert.AlertType.INFORMATION);
                    a.setContentText(ex.getMessage());
                    a.show();
                }
            }


        } else {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("Nome do Jogador ou numero da camisa invalido");
            a.show();
        }
    }
    
    private void atualizarTabelaJogadores() {
        this.jogadoresDaEquipe = FXCollections.observableArrayList(e.getJogadores().entrySet());
        this.jogadores.setItems(jogadoresDaEquipe);
        this.nomeJogador.clear();
        this.camisaJogador.clear();
    }

    private void removerJogador() {
        if (jogadores.getSelectionModel().getSelectedItem() != null) {
            try {
                e.removerJogador(jogadores.getSelectionModel().getSelectedItem().getValue());
                jogadoresDaEquipe = FXCollections.observableArrayList(e.getJogadores().entrySet());
                jogadores.setItems(jogadoresDaEquipe);
            } catch (JNPEException ex) {
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setContentText(ex.getMessage());
                a.show();
            }
        } else {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("Jogador não selecionado");
            a.show();
        }

    }

    private void simularCampeonato() {
        if (!nomeCampeonato.getText().isEmpty()) {
            try {
                cControl.simularCampeonato(equipesEscolhidas, dataInicioCampeonato.getValue(), nomeCampeonato.getText());
                campeonatos.setItems(FXCollections.observableList(cControl.listar()));
                campeonatos1.setItems(FXCollections.observableList(cControl.listar()));
            } catch (CAEException ex) {
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setContentText(ex.getMessage());
                a.show();
            } catch (CNAException ex) {
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setContentText(ex.getMessage());
                a.show();
            }
        } else {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("Nome Invalido");
            a.show();
        }


    }

    private void adicionarEquipeCampeonato() {
        if (equipesLivres.getSelectionModel().getSelectedItem() != null) {
            Equipe e = equipesLivres.getSelectionModel().getSelectedItem();
            equipesEscolhidas.add(e);
            equipesLivresLista.remove(e);
            equipesLivres.setItems(FXCollections.observableList(equipesLivresLista));
            equipesParticipantes.setItems(FXCollections.observableList(equipesEscolhidas));
        } else {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("Equipe não selecionada");
            a.show();
        }

    }

    private void removerEquipeCampeonato() {
        if (equipesParticipantes.getSelectionModel().getSelectedItem() != null) {
            equipesEscolhidas.remove(equipesParticipantes.getSelectionModel().getSelectedItem());
            equipesLivresLista.add(equipesParticipantes.getSelectionModel().getSelectedItem());
            equipesLivres.setItems(FXCollections.observableList(equipesLivresLista));
            equipesParticipantes.setItems(FXCollections.observableList(equipesEscolhidas));
        } else {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("Equipe não selecionada");
            a.show();
        }
    }

}
