package com.freedempire;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This is a simple calculator using JavaFX library.
 * @version 1.0
 * @author Freedempire
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("gui.fxml"));
        primaryStage.setTitle("My Calculator");
        Scene scene = new Scene(root);
        scene.getStylesheets().add("com/freedempire/gui.css");
        primaryStage.setScene(scene);
        primaryStage.resizableProperty().setValue(false); // disable window resizing
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
