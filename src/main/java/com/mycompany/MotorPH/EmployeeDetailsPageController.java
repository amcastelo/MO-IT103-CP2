package com.mycompany.MotorPH;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.collections.ObservableList;
/**
 * FXML Controller class
 *
 * @author adamm
 */
public class EmployeeDetailsPageController implements Initializable, MainControllerAware {
    @FXML
    private TableView<ObservableList<String>> EmpTable;
    @FXML
    private TextField empIDField;
    @FXML
    private Button searchButton;
    
    private MainPageController mainController;
    
    private EmployeeFileManager empFileManager = new EmployeeFileManager();
    
    private static String TXT_FILE_PATH = "src/main/resources/Data.txt";
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       empFileManager.loadCached(EmpTable);
    }
    
    @Override
    public void setMainController(MainPageController mainController) {
        this.mainController = mainController;
}    
    
    @FXML
    private void onSearchButtonClick() {
    String searchEmpNum = empIDField.getText().trim();

    ObservableList<ObservableList<String>> filteredRows = FXCollections.observableArrayList();

        for (ObservableList<String> row : EmpTable.getItems()) {
            if (!row.isEmpty() && row.get(0).equals(searchEmpNum)) {
                filteredRows.add(row);
            }
        }
        
        if (filteredRows.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Employee ID not found.");
            alert.showAndWait();
        } else {
            EmpTable.setItems(filteredRows);
        }
    }    
    
    @FXML
    private void onReloadButtonClick(){
        empFileManager.loadCached(EmpTable);
    }
}
