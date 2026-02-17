package com.mycompany.myapp;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

/**
 * FXML Controller class
 *
 * @author D1mis
 */
public class SettingsController implements Initializable {
    @FXML
    private TextField First_N;
    @FXML
    private TextField Last_N;
    @FXML
    private DatePicker age_T;
    @FXML
    private TextField email_T;
    @FXML
    private TextField phone_T;
    @FXML
    private TextField city_T;
    @FXML
    private PasswordField current_P;
    @FXML
    private PasswordField new_P;
    @FXML
    private ImageView image_Country;
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
    private Label saveL;
    @FXML
    private Label saveR;
    @FXML
    private AnchorPane mainM;
    @FXML
    private MenuButton countryS,genderS;
    
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
    
    public void exit() {
        Navigation.exit();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        DatabaseConnector db = new DatabaseConnector();
        db.Connect();
        
        Image img = new Image(Session.getUser_URL());
        cim.setFill(new ImagePattern(img));
        
        String username = " " + Session.getUsername();
        profileB.setText(username);
        
        Last_N.setText(Session.getLast_Name());
        First_N.setText(Session.getFirst_Name());
        email_T.setText(Session.getEmail());
        phone_T.setText(Session.getPhone());
        age_T.setValue(Session.getAge());
        genderS.setText(Session.getGender());
        city_T.setText(Session.getCity());
        countryS.setText(Session.getCountry());
        Image img_Country = new Image(getClass().getResource(Session.getCountry_URL()).toExternalForm());
        image_Country.setImage(img_Country);
    }
    
    @FXML
    private void countrySelect(ActionEvent event) {
        MenuItem source = (MenuItem) event.getSource();
        countryS.setText(source.getText());
        countryS.setGraphic(source.getGraphic());
    }
    
    @FXML
    private void countryGender(ActionEvent event) {
        MenuItem source = (MenuItem) event.getSource();
        genderS.setText(source.getText());
        genderS.setGraphic(source.getGraphic());
    }
    
    public void save(ActionEvent event) throws IOException {
        DatabaseConnector db = new DatabaseConnector();
        db.Connect();
        String email = email_T.getText();
        String F_N = First_N.getText();
        String L_N = Last_N.getText();
        String phone = phone_T.getText();
        LocalDate age = age_T.getValue();
        String gender = genderS.getText();
        String city_n = city_T.getText();
        String country = countryS.getText();
        String c_p = current_P.getText();
        String n_p = new_P.getText();
        boolean isDone = db.updateSettings(email, F_N, L_N, phone, age, gender, city_n, country, c_p, n_p);
        if(isDone){
            Image img_Country = new Image(getClass().getResource(Session.getCountry_URL()).toExternalForm());
            image_Country.setImage(img_Country);
            countryS.setText(Session.getCountry());
            saveR.setVisible(false);
            saveL.setVisible(true);
        }
        else{
            saveL.setVisible(false);
            saveR.setVisible(true);
        }
    }
    
}