/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.myapp;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;
import javafx.util.Duration;
/**
 * FXML Controller class
 *
 * @author D1mis
 */
public class ProgressController implements Initializable {
    
    @FXML
    private ProgressBar Progressbar;
    double progress;
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
            Timeline timeline = new Timeline(
            new KeyFrame(Duration.millis(250), e -> {
                progress = Progressbar.getProgress();
                Progressbar.setProgress(progress + 0.1);
            })
        );
        timeline.setCycleCount(10);
        timeline.play();
        timeline.setOnFinished(e -> {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("Recipe.fxml"));
                Stage stage = (Stage) Progressbar.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.centerOnScreen();
    } catch (IOException ex) {
        ex.printStackTrace();
    }
    });
    }
}
