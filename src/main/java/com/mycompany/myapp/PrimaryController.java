package com.mycompany.myapp;

import java.io.IOException;
import javafx.fxml.FXML;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;


public class PrimaryController implements Initializable{

    @FXML
    private Label See,See3,wrongP,wrongU;
    @FXML
    private Rectangle rectangle;
    @FXML
    private ImageView loginImage;
    @FXML
    private Button ButtonL;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        TranslateTransition translate = new TranslateTransition();
        TranslateTransition translateL = new TranslateTransition();
        TranslateTransition translateL2 = new TranslateTransition();        

        
        translate.setNode(rectangle);
        translate.setDuration(Duration.millis(1500));
        translate.setByY(-530);
        
        translateL.play();
        translateL2.play();
        translate.play();
        
        FadeTransition fade = new FadeTransition();
        FadeTransition fadeL = new FadeTransition();
        FadeTransition fadeL3 = new FadeTransition();
        FadeTransition fadeImage = new FadeTransition();
        
        fadeL.setNode(See);
        fadeL.setDuration(Duration.millis(3500));
        fadeL.setFromValue(0);
        fadeL.setToValue(1);
        
        fadeL3.setNode(See3);
        fadeL3.setDuration(Duration.millis(5500));
        fadeL3.setFromValue(0);
        fadeL3.setToValue(1);
        
        fadeImage.setNode(loginImage);
        fadeImage.setDuration(Duration.millis(2500));
        fadeImage.setFromValue(0);
        fadeImage.setToValue(1);
        
        fadeL.play();
        fadeL3.play();
        fadeImage.play();
    }
    
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    public void login(ActionEvent event) throws IOException{
        String usern = username.getText().trim();
        String pass = password.getText().trim();
        
        DatabaseConnector db = new DatabaseConnector();
        db.Connect();
        boolean isValid = db.validateLogin(usern, pass);
        
        
        if((usern.isEmpty() || pass.isEmpty()) || (isValid == false)){
            wrongP.setVisible(true);
            wrongU.setVisible(true);
            username.setStyle("-fx-border-color:red;");
            password.setStyle("-fx-border-color:red;");
        }
        else{
            Session.setUsername(usern);
            db.setInfo();
            root = FXMLLoader.load(getClass().getResource("Progress.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }
    
    @FXML 
    private void openPopup() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("popUpCreate.fxml"));
        root = loader.load();
        
        popUpCreate popUpcreate = loader.getController();
   
        Stage popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.initStyle(StageStyle.TRANSPARENT);
        popUpcreate.setStage(popupStage);
        
        scene = new Scene(root);
        
        popupStage.setScene(scene);
        popupStage.show();
    }  
}
