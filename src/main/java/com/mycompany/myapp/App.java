package com.mycompany.myapp;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

/**
 * JavaFX App
 */
public class App extends Application {
    public static Stage stage;
    
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        this.stage = stage;
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene scene = new Scene(root,1280,720,Color.WHITE);
        String css = this.getClass().getResource("style.css").toExternalForm();
        scene.getStylesheets().add(css);
        Image icon = new Image("icons8-cooking-64.png");
        stage.getIcons().add(icon);
        stage.setTitle("Recipe organizer");
        stage.setScene(scene);
        stage.show();
    }


}