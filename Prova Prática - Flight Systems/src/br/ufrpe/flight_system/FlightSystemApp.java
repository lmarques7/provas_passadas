package br.ufrpe.flight_system;

import br.ufrpe.flight_system.gui.ScreenManager;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class FlightSystemApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(ScreenManager.getInstance().getMainScene());
        primaryStage.setTitle("Flight and ticket management system");
        
        primaryStage.setWidth(800);
        primaryStage.setHeight(534);
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image("airplane-57-16.png"));
        
        ScreenManager.getInstance().setPrimaryStage(primaryStage);
        
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        FlightSystemApp.launch(args);
    }

}
