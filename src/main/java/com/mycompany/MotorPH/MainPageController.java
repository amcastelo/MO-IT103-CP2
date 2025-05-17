package com.mycompany.MotorPH;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.util.Duration;
import java.io.IOException;

/**
 * FXML Controller class
 *
 * @author adamm
 */
public class MainPageController {
    
    @FXML
    private StackPane navDrawerContainer;

    @FXML
    private StackPane contentContainer;
    
    // Initialize the app
    public void initialize() throws IOException {
    // Load navigation drawer FXML with FXMLLoader instance (not static load)
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/NavDrawer.fxml"));
    Parent navDrawer = loader.load();  // load the drawer UI

    // Get the controller instance of NavDrawer
    NavDrawerController navDrawerController = loader.getController();
    
    // Pass this MainController to NavDrawerController
    navDrawerController.setMainController(this);
    // Add the nav drawer UI to the container
    navDrawerContainer.getChildren().add(navDrawer);

}
    
    // Content loader for content container.
    public void loadContent(String fxmlPath) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
        Parent content = loader.load();

        Object controller = loader.getController();
            if (controller instanceof MainControllerAware) {
                ((MainControllerAware) controller).setMainController(this);
            }
            
        contentContainer.getChildren().setAll(content);
    }
    
    // Navigation container animation resize to be inline with navigation drawer on enter
    public void navEnter() {
        Timeline timeline = new Timeline();
        KeyValue widthValue = new KeyValue(navDrawerContainer.prefWidthProperty(), 200);
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(.15), widthValue);
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
    }
    
    // Navigation container animation resize to be inline with navigation drawer on exit
    public void navExit() {
        Timeline timeline = new Timeline();
        KeyValue widthValue = new KeyValue(navDrawerContainer.prefWidthProperty(), 50);
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(.15), widthValue);
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
    }
    


}
    

