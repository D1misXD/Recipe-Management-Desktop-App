/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp;

import java.io.IOException;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 *
 * @author D1mis
 */
public class Navigation {
    
    public static void navigate(String fxml) { 
    try {
        Parent root = FXMLLoader.load(Navigation.class.getResource(fxml));
        Scene scene = new Scene(root);

        App.stage.setScene(scene);
        App.stage.show();

    } catch (IOException e) {
        e.printStackTrace();
    }
}
    
    public static void exit() {
        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setTitle("Confirm Logout");
        confirmation.setHeaderText("Logout");
        confirmation.setContentText("Are you sure you want to logout?");
        Optional<ButtonType> result = confirmation.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            App.stage.close();
        }
    }
    
    public static void Logout(String fxml){
        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setTitle("Confirm Logout");
        confirmation.setHeaderText("Logout");
        confirmation.setContentText("Are you sure you want to logout?");
        Optional<ButtonType> result = confirmation.showAndWait();
    
        if (result.isPresent() && result.get() == ButtonType.OK) {
            navigate(fxml);
        }
        
    }
}
