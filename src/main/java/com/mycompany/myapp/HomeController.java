package com.mycompany.myapp;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
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
public class HomeController implements Initializable {
    
    @FXML
    private Circle cim;
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
    private ImageView homeImage;
    @FXML
    private AnchorPane mainM;
    @FXML
    private Label label1,label2;
    
    public void Home() {
        Navigation.navigate("Home.fxml");
    }

    public void Recipes() {
        Navigation.navigate("Recipe.fxml");
    }

    public void Account() {
        Navigation.navigate("Account.fxml");
    }

    public void Settings() {
        Navigation.navigate("Settings.fxml");
    }

    public void Logout() {
        Navigation.Logout("Login.fxml");
    }
    
    public void exit(ActionEvent event) {
        Navigation.exit();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        DatabaseConnector db = new DatabaseConnector();
        db.Connect();
        
        Image img = new Image(Session.getUser_URL());
        cim.setFill(new ImagePattern(img));
        
        FadeTransition fade = new FadeTransition();
        FadeTransition fade2 = new FadeTransition();
        FadeTransition fade3 = new FadeTransition();
        
        fade.setNode(homeImage);
        fade.setDuration(Duration.millis(1500));
        fade.setFromValue(0);
        fade.setToValue(1);
        
        fade2.setNode(label1);
        fade2.setDuration(Duration.millis(1500));
        fade2.setFromValue(0);
        fade2.setToValue(1);
        
        fade3.setNode(label2);
        fade3.setDuration(Duration.millis(1500));
        fade3.setFromValue(0);
        fade3.setToValue(1);
        
        fade.play();
        fade2.play();
        fade3.play();
        
        String username = " " + Session.getUsername();
        profileB.setText(username);
    }
}
