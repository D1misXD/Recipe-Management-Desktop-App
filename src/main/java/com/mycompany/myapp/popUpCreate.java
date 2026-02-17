package com.mycompany.myapp;

import java.io.File;
import java.time.LocalDate;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class popUpCreate {
    
    @FXML
    private Stage stage;

    @FXML
    private DatePicker AgeS;

    @FXML
    private TextField CityS;

    @FXML
    private TextField EmailS;

    @FXML
    private TextField F_NS;

    @FXML
    private TextField L_NS;

    @FXML
    private PasswordField PasswordS;

    @FXML
    private TextField PhoneS;

    @FXML
    private TextField UsernameS;

    @FXML
    private Button addB;

    @FXML
    private Button cancelB;

    @FXML
    private MenuButton countryS;

    @FXML
    private MenuButton genderS;

    @FXML
    private PasswordField passwordRS;

    @FXML
    private Button photoS;

    @FXML
    private Button popUpExitB;
    
    @FXML
    private Image selectedImage;
    
    private String imageURL;
    
    private MenuController menuController;
    
    public void setMainController(MenuController mainController) {
        this.menuController = mainController;
    }
    public void setStage(Stage stage) {
        this.stage = stage;
    }
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
                selectedImage = new Image(file.toURI().toString());
            
                imageURL = file.getName();
                
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
    void countrySelect(ActionEvent event) {
        MenuItem source = (MenuItem) event.getSource();
        countryS.setText(source.getText());
    }

    @FXML
    void genderSelect(ActionEvent event) {
        MenuItem source = (MenuItem) event.getSource();
        genderS.setText(source.getText());
    }

    @FXML
    void exit() {
        if (stage != null) {
            stage.close();
        }
    }
    
    @FXML
    void registerButton(){
        DatabaseConnector db = new DatabaseConnector();
        db.Connect();
        LocalDate age = AgeS.getValue();
        String city_n = CityS.getText();
        String phone = PhoneS.getText();
        String F_N = F_NS.getText();
        String L_N = L_NS.getText();
        String email = EmailS.getText();
        String username = UsernameS.getText();
        String country = countryS.getText();
        String password = PasswordS.getText();
        String passwordR = passwordRS.getText();
        String gender = genderS.getText();
        if(!email.equals("") && !F_N.equals("") && !L_N.equals("") && !phone.equals("") && age != null && !gender.equals("Select") && !city_n.equals("Select") && !country.equals("") && imageURL != null && password.equals(passwordR)){
            boolean isDone = db.createUser(email, F_N, L_N, phone, age, gender, city_n, country, password, passwordR, imageURL, username);
            if(isDone){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText("Registered");
                alert.setContentText("Your account has been created and now you can procced to login!");
                alert.showAndWait();
                exit();
            }
        }
        else if(imageURL == null){
            new Alert(Alert.AlertType.ERROR, "Please pick a profile picture!").show();
        }
        else if(!password.equals(passwordR)){
            new Alert(Alert.AlertType.ERROR, "Your Password is not consistent!").show();
        }
        else{
            new Alert(Alert.AlertType.ERROR, "Do not leave empty spaces!").show();
        }
    }
}