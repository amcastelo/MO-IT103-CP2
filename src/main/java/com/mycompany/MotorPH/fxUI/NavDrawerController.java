/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.MotorPH.fxUI;

import com.mycompany.MotorPH.fxUI.MainPageController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.scene.image.*;
import javafx.scene.input.*;
import javafx.event.*;
import javafx.animation.*;
import javafx.util.*;
import javafx.collections.*;
import javafx.beans.property.SimpleStringProperty;

import java.io.BufferedReader;
import java.io.FileReader;

import java.io.IOException;
/**
 * FXML Controller class
 *
 * @author adamm
 */
public class NavDrawerController implements Initializable {
    @FXML
    private VBox sideMenu;
    @FXML
    private Button buttonProfile, buttonProfile1, buttonProfile2;
    @FXML
    private TableView tableView;
    
    private MainPageController mainController;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        sideMenu.setPrefWidth(50);
        
        setButtonIcon(buttonProfile, "/icons/Profile.PNG", 40, 40);      
        setButtonIcon(buttonProfile1, "/icons/Employee.PNG", 40, 40);
        setButtonIcon(buttonProfile2, "/icons/Payroll.PNG", 40, 40);
        
        setAllButtonsToGraphicOnly();
    }
    
    // Links this class controller to main page controller when loaded in scene.
    public void setMainController(MainPageController mainController) {
    this.mainController = mainController;
}
    
    // Reusable method to set button icon
    private void setButtonIcon(Button button, String imagePath, double width, double height) {
        Image image = new Image(imagePath, width, height, true, true);
        ImageView imageView = new ImageView(image);
        button.setGraphic(imageView);
        button.setContentDisplay(ContentDisplay.LEFT);
    }
    
    // Set buttons to icon only on navigation drawer collapse
    private void setAllButtonsToGraphicOnly() {
        buttonProfile.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        buttonProfile1.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        buttonProfile2.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
    }
    
    // Set buttons to text and icon on navigation drawer expand
    private void setAllButtonsToTextAndGraphic() {
        buttonProfile.setContentDisplay(ContentDisplay.TEXT_ONLY);
        setButtonIcon(buttonProfile, "/icons/Profile.PNG", 40, 40);

        buttonProfile1.setContentDisplay(ContentDisplay.TEXT_ONLY);
        setButtonIcon(buttonProfile1, "/icons/Employee.PNG", 40, 40);

        buttonProfile2.setContentDisplay(ContentDisplay.TEXT_ONLY);
        setButtonIcon(buttonProfile2, "/icons/Payroll.PNG", 40, 40);
    }
    
    // animation for navigation drawer/sidemenu on mouse hover
    public void sideMenuResizeEnter() {
        Timeline timeline = new Timeline();
        KeyValue widthValue = new KeyValue(sideMenu.prefWidthProperty(), 200);
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(.15), widthValue);
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
        setAllButtonsToTextAndGraphic();
    }
    
    // animation for navigation drawer/sidemenu on mouse exit
    public void sideMenuResizeExit() {
        Timeline timeline = new Timeline();
        KeyValue widthValue = new KeyValue(sideMenu.prefWidthProperty(), 50);
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(.15), widthValue);
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
        setAllButtonsToGraphicOnly();
    }   
    
    // Navigate to employee details page
    public void clickEmployee(ActionEvent event) throws IOException {
        mainController.loadContent("/fxml/EmployeeDetailsPage.fxml");
    }
    
    // navigate to payroll page
    public void clickPayroll(ActionEvent event) throws IOException {
        mainController.loadContent("/fxml/PayrollPage.fxml");
    }   

}


