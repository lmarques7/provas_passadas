/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.codersfootballleague.gui;

import br.ufrpe.codersfootballleague.negocios.CampeonatoController;
import br.ufrpe.codersfootballleague.negocios.EquipeController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

public class CodersFootballLeague extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        EquipeController eControl = EquipeController.getInstance();
        CampeonatoController cControl = CampeonatoController.getInstance();
        TabPane p = FXMLLoader.load(getClass().getResource("Tela.fxml"));
        Scene s = new Scene(p);
        primaryStage.setTitle("Coders Football League");
        primaryStage.setScene(s);
        primaryStage.setResizable(false);
        primaryStage.show();
        primaryStage.setOnCloseRequest(((event) -> {
            event.consume();
            eControl.salvar();
            cControl.salvar();
            primaryStage.close();
            Platform.exit();
            System.exit(0);
        }));
    }

    public static void main(String[] args) {
        launch();
    }
}
