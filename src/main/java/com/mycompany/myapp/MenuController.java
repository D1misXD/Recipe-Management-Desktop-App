package com.mycompany.myapp;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author D1mis
 */
public class MenuController implements Initializable {
    DatabaseConnector db = new DatabaseConnector();
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
    private AnchorPane mainM;

    private Scene scene;
    private Stage stage;
    private Parent root;
    
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
    
    @FXML 
    private void openPopUpAdd() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("popUpAdd.fxml"));
        root = loader.load();
        
        popUpAdd popUpAdd = loader.getController();
   
        Stage popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.initStyle(StageStyle.TRANSPARENT);
        popUpAdd.setStage(popupStage);
        popUpAdd.setMainController(this);
        
        scene = new Scene(root);
        
        popupStage.setScene(scene);
        popupStage.show();
    }  
    
    private void openPopUpEdit(Recipe recipe) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("popUpEdit.fxml"));
        root = loader.load();
    
        
        popUpEditController popupeditcontroller = loader.getController();
        popupeditcontroller.setRecipeToEdit(recipe);
        popupeditcontroller.setMainController(this);

        Stage popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.initStyle(StageStyle.TRANSPARENT);
        popupeditcontroller.setStage(popupStage);
        
    
        scene = new Scene(root);
        popupStage.setScene(scene);
        popupStage.show();
    }  
    
    @FXML 
    private void filterWindow() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("popUpFilter.fxml"));
        root = loader.load();
        
        popUpFilterController popUpController = loader.getController();
        
        Stage popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.initStyle(StageStyle.TRANSPARENT);
        
        popupStage.setX(1100); 
        popupStage.setY(200); 
        
        popUpController.setStage(popupStage);
        popUpController.setMainController(this);
        
        scene = new Scene(root);
        
        popupStage.setScene(scene);
        popupStage.show();
    } 
    
    // tableview
    @FXML
    private TableColumn<Recipe, String> tPhoto;
    @FXML 
    private TableColumn<Recipe, String> tName;
    @FXML
    private TableColumn<Recipe, String> tIngredientsC;
    @FXML
    private TableColumn<Recipe, String> tCategory;
    @FXML
    private TableColumn<Recipe, String> tCalories;
    @FXML
    private TableColumn<Recipe, String> tDifficulty;
    @FXML
    private TableColumn<Recipe, String> tDescription;
    @FXML
    private TableColumn<Recipe, Void> tAction;
    @FXML
    private TableView<Recipe> table;
    @FXML
    private TextField searchB;
    
    private ObservableList<Recipe> masterData = FXCollections.observableArrayList();
    private FilteredList<Recipe> filteredData = new FilteredList<>(masterData);
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        db.Connect();
        
        Image img = new Image(Session.getUser_URL());
        cim.setFill(new ImagePattern(img));
        
        String username = " " + Session.getUsername();
        profileB.setText(username);

        //tableview
        tName.setCellValueFactory(new PropertyValueFactory<Recipe, String>("Name"));
        tIngredientsC.setCellValueFactory(new PropertyValueFactory<Recipe, String>("IngredientsC"));
        tCategory.setCellValueFactory(new PropertyValueFactory<Recipe, String>("Category"));
        tCalories.setCellValueFactory(new PropertyValueFactory<Recipe, String>("Calories"));
        tDifficulty.setCellValueFactory(new PropertyValueFactory<Recipe, String>("Difficulty"));
        tDescription.setCellValueFactory(new PropertyValueFactory<Recipe, String>("Description"));
        //tphoto CREATION FOR IMAGES
        tPhoto.setCellValueFactory(new PropertyValueFactory<>("image")); 
        tPhoto.setCellFactory(column -> new TableCell<Recipe, String>() {
        private final ImageView imageView = new ImageView();
        private final Circle clip = new Circle();
        {
            imageView.setFitHeight(70);
            imageView.setFitWidth(70);
            imageView.setPreserveRatio(false);
            
            clip.setRadius(35); 
            clip.setCenterX(35);
            clip.setCenterY(35);
            imageView.setClip(clip);
        }
        
        @Override
        protected void updateItem(String imageURL, boolean empty) {
            super.updateItem(imageURL, empty);
            if (empty || imageURL.equals("")) {
                setGraphic(null);
            } 
            else {
                Image image = new Image(imageURL, true);
                imageView.setImage(image);
                setGraphic(imageView);
                setAlignment(Pos.CENTER);
            }
        }
        });
        //tAction buttons
        tAction.setCellFactory(column -> new TableCell<Recipe, Void>() {
        private final ImageView editIcon = new ImageView(new Image(getClass().getResourceAsStream("/edit-icon.png")));
        private final ImageView deleteIcon = new ImageView(new Image(getClass().getResourceAsStream("/delete-icon.png")));
        private final Button editBtn = new Button();
        private final Button deleteBtn = new Button();
        {
            editIcon.setFitWidth(18);
            editIcon.setFitHeight(18);
            deleteIcon.setFitWidth(18);
            deleteIcon.setFitHeight(18);
            editBtn.setGraphic(editIcon);
            deleteBtn.setGraphic(deleteIcon);
            editBtn.setStyle("-fx-background-color: #f8f9fa;" + "-fx-cursor: hand;" + "-fx-border-radius: 20px;" + "-fx-background-radius: 10px");
            deleteBtn.setStyle("-fx-background-color: #f8f9fa;" + "-fx-cursor: hand;" + "-fx-border-radius: 20px;" + "-fx-background-radius: 10px");
            editBtn.setOnAction(e -> { 
            Recipe recipe = getTableView().getItems().get(getIndex());
            try {
                openPopUpEdit(recipe);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            });
            deleteBtn.setOnAction(e -> {
                Recipe recipe = getTableView().getItems().get(getIndex());
                deleteRecipe(recipe);
            });
        }
        private final HBox buttonsContainer = new HBox(5, editBtn, deleteBtn);
        {
            buttonsContainer.setAlignment(Pos.CENTER);
        }
        //tAction for empty cell bug
        @Override
        protected void updateItem(Void item, boolean empty) {
            super.updateItem(item, empty);
        
            
            if (empty) {
               setGraphic(null);
            } 
            else {
                setGraphic(buttonsContainer);
            }
        }
        });
        db.getRecipes(this);
        table.setItems(filteredData);
        setupSearch();
    }

    public void addRecipeToTable(Recipe recipe) {
        db.Connect();
        boolean isDone = db.createRecipe(recipe);
        if(isDone){
            masterData.add(recipe);
        }
    }
    
    public void updateRecipe(Recipe oldRecipe, Recipe updatedRecipe) {
        int masterIndex = masterData.indexOf(oldRecipe);
        db.editRecipe(updatedRecipe);
        masterData.set(masterIndex, updatedRecipe);
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText("Updated");
        alert.setContentText("Your recipe has been saved!");
    
        alert.showAndWait(); 
    }
    
    public void deleteRecipe(Recipe recipe) {
        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setTitle("Confirm Deletion");
        confirmation.setHeaderText("Delete Recipe");
        confirmation.setContentText("Are you sure you want to delete " + recipe.getName() + "?");
        Optional<ButtonType> result = confirmation.showAndWait();
    
        if (result.isPresent() && result.get() == ButtonType.OK) {
            db.deleteRecipe(recipe);
            masterData.remove(recipe);
        }
    }
    
    public void setupRecipes(Recipe recipe){
        masterData.add(recipe);
    }
    
    private void setupSearch() {
        searchB.textProperty().addListener((observable, oldValue, newValue) -> {
        filteredData.setPredicate(recipe -> {
            if (newValue == null || newValue.isEmpty()) {
                return true;
            }
            
            String lowerCaseFilter = newValue.toLowerCase();
            
            if (recipe.getName().toLowerCase().contains(lowerCaseFilter)) {
                return true;
            }
            
            return false;
        });
        });        
    }
    
    public void applyFilters(Set<String> difficulties, 
                           Set<String> categories) {
        
        filteredData.setPredicate(recipe -> {
            if(!difficulties.isEmpty() && !difficulties.contains(recipe.getDifficulty())){
                return false;
            }
            if(!categories.isEmpty() && !categories.contains(recipe.getCategory())){
                return false;
            }
            return true;
    });
    }
}
