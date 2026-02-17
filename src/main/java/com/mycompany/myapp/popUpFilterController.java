package com.mycompany.myapp;

import java.util.HashSet;
import java.util.Set;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ToggleButton;
import javafx.stage.Stage;

public class popUpFilterController {
    private MenuController menuController;
    private Scene scene;
    private Stage stage;
    private Parent root;
    
    @FXML
    private CheckBox easyB,mediumB,hardB;
    @FXML
    private ToggleButton dessertB,breakfastB,lunchB,saladB,appetizerB,dinnerB;
    @FXML
    private Button saveB,cancelB;
    
    private MenuController mainController;
    

    
    public void setMainController(MenuController mainController) {
        this.menuController = mainController;
    }
    
   
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    
    @FXML
    private void exit() {
        if (stage != null) {
            stage.close();
        }
    }
    @FXML
    private void apply() {
        Set<String> difficulties = new HashSet<>();
        if (easyB.isSelected()) difficulties.add("Easy");
        if (mediumB.isSelected()) difficulties.add("Medium");
        if (hardB.isSelected()) difficulties.add("Hard");

        Set<String> categories = new HashSet<>();
        if (breakfastB.isSelected()) categories.add("Breakfast");
        if (lunchB.isSelected()) categories.add("Lunch");
        if (dinnerB.isSelected()) categories.add("Dinner");
        if (dessertB.isSelected()) categories.add("Dessert");
        if (saladB.isSelected()) categories.add("Salad");
        if (appetizerB.isSelected()) categories.add("Appetizer");

        
        menuController.applyFilters(difficulties, categories);

        exit();
    }
    }
