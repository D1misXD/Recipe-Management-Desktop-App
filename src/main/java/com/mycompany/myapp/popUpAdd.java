/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.myapp;

import java.io.File;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class popUpAdd {
    @FXML
    private Circle cimP;
    private Stage stage;
    @FXML
    private Button addB;

    @FXML
    private TextField caloriesS;

    @FXML
    private Button cancelB;

    @FXML
    private MenuButton categoryS;

    @FXML
    private TextField descriptionS;

    @FXML
    private MenuButton difficultyS;

    @FXML
    private TextField ingredientCS;
    @FXML
    private Button photoS;
    private Image selectedImage;
    @FXML
    private TextField nameS;
    private MenuController menuController;
    
    public void setMainController(MenuController mainController) {
        this.menuController = mainController;
    }
    
    private String imageURL;
    @FXML
    private void photoSelect() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Recipe Image");
        fileChooser.getExtensionFilters().addAll(
        new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
        );
        File file = fileChooser.showOpenDialog(photoS.getScene().getWindow());
        if (file != null) {
            try {
                imageURL = file.toURI().toString();
                selectedImage = new Image(file.toURI().toString());
            
                ImageView imageView = new ImageView(selectedImage);
                imageView.setPreserveRatio(false);
                imageView.setFitHeight(90);
                imageView.setFitWidth(100);
            
            
                Circle clip = new Circle(45);
                clip.setCenterX(50);
                clip.setCenterY(45);
                imageView.setClip(clip);
            
            
                photoS.setGraphic(imageView);
            } 
            catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Failed to load image").show();
            }
        }
    }
    @FXML
    private void categorySelect(ActionEvent event) {
        MenuItem source = (MenuItem) event.getSource();
        categoryS.setText(source.getText());
    }

    @FXML
    private void difficultySelect(ActionEvent event) {
        MenuItem source = (MenuItem) event.getSource();
        difficultyS.setText(source.getText());
    }
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    
    @FXML
    private void add() {
        try{
        if (selectedImage == null ||categoryS.getText().equals("Select") || difficultyS.getText().equals("Select") || nameS.getText().equals("") || descriptionS.getText().equals("") || ingredientCS.getText().equals("") || caloriesS.getText().equals("")) {
        new Alert(Alert.AlertType.WARNING, "Please fill all the spaces with the '*' symbol on them!").show();
        return;
        }
       
        String name = nameS.getText().trim();
        int ingredients = Integer.parseInt(ingredientCS.getText().trim());
        int calories = Integer.parseInt(caloriesS.getText().trim());
        String category = categoryS.getText().trim();
        String difficulty = difficultyS.getText().trim();
        String description = descriptionS.getText().trim();
        
        
        Recipe newRecipe = new Recipe(name, ingredients, calories, category, difficulty, description, imageURL, Session.getUSER_ID());
        
   
        menuController.addRecipeToTable(newRecipe);
        exit();
        } catch (NumberFormatException ex){
            new Alert(Alert.AlertType.ERROR, "Make sure calories and ingredients are numbers.").show();
        }
    }
    @FXML
    private void exit() {
        if (stage != null) {
            stage.close();
        }
    }
    }