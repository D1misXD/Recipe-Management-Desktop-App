package com.mycompany.myapp;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author D1mis
 */
public class AccountController implements Initializable {
    @FXML
    private Label account_Name_LB;
    @FXML
    private Label RP_COUNT_LB;
    @FXML
    private Label label_location;
    @FXML
    private Circle cim;
    @FXML
    private Circle cim1;
    @FXML
    private ImageView imageP;
    @FXML
    private ImageView image_Country;
    @FXML
    private Button profileB;
    @FXML
    private Button menub;
    @FXML
    private Button recipeb;
    @FXML
    private Button accountb;
    @FXML
    private Button settingsb;
    @FXML
    private Button logoutb;
    @FXML
    private MenuButton menub1;
    @FXML
    private AnchorPane mainM;
    
    public void Home() {
        Navigation.navigate("Home.fxml");
    }

    public void Recipes() {
        Navigation.navigate("Recipe.fxml");
    }

    public void Account() {
        Navigation.navigate("Account.fxml");
    }

    public void Settings(ActionEvent event) {
        Navigation.navigate("Settings.fxml");
    }

    public void Logout(ActionEvent event) {
        Navigation.Logout("Login.fxml");
    }
    
    public void exit() {
        Navigation.exit();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        DatabaseConnector db = new DatabaseConnector();
        db.Connect();
        
        Image img = new Image(Session.getUser_URL());
        cim.setFill(new ImagePattern(img));
        cim1.setFill(new ImagePattern(img));
        
        label_location.setText(Session.getCountry() + ", " + Session.getCity());
        RP_COUNT_LB.setText("Number of Recipes: " + Session.getRecipes());
        account_Name_LB.setText(Session.getFirst_Name() + " " + Session.getLast_Name());
        
        Image img_Country = new Image(getClass().getResource(Session.getCountry_URL()).toExternalForm());
        image_Country.setImage(img_Country);
        
        TranslateTransition translate = new TranslateTransition();
        FadeTransition fade = new FadeTransition();
        
        translate.setNode(cim1);
        translate.setDuration(Duration.millis(700));
        translate.setByX(-227);
        
        
        fade.setNode(imageP);
        fade.setDuration(Duration.millis(1500));
        fade.setFromValue(0);
        fade.setToValue(1);
        
        fade.play();
        translate.play();
        
        String username = " " + Session.getUsername();
        profileB.setText(username);
        
    }
    
}