package com.mycompany.MotorPH.fxUI;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

import com.mycompany.MotorPH.Grosswage;
import com.mycompany.MotorPH.Netwage;
import com.mycompany.MotorPH.fxUI.MainPageController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

/**
 * FXML Controller class
 *
 * @author adamm
 */
public class PayrollPageController implements Initializable, MainControllerAware {
    
    private MainPageController mainController;
    
    @FXML
    private TabPane payrollTab;
    
    @FXML
    private Tab grossTab, netTab;
    
    @FXML
    private TextField empNameField, empIDField, payrollMonthField, grossHourlyField, grossTotalHoursField, grossWageField, netSSSField, netPhilhealthField, netPagibigField,
            netLateField, netTotalDeductionsField, netTotalHoursField, netGrossField, netTaxableField, netWithholdingTaxField, netWageField;
    
    private Grosswage grosswage = new Grosswage();
    private Netwage netwage = new Netwage();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    @Override
    public void setMainController(MainPageController mainController) {
        this.mainController = mainController;
}    
    
    @FXML
    private void onCalculateButtonClick() {
        Tab selectedTab = payrollTab.getSelectionModel().getSelectedItem();
        String empID = empIDField.getText();
        String monthText = payrollMonthField.getText();

        // Validate inputs and get parsed month
        Integer month = inputValidation(empID, monthText);
            if (month == null) return; // stop execution if invalid input           
        
            if (selectedTab == grossTab) {
                grosswage.calculate(empID, month);
                printGross();
            } else if (selectedTab == netTab) {
                netwage.calculate(empID, month);
                printNet();
            } else {
                showAlert(Alert.AlertType.WARNING, "Unknown Tab", "Selected tab is unhandled: " + selectedTab.getText());
            }
    }



    private void printGross(){
        empNameField.setText(grosswage.getEmployeeName());
        grossHourlyField.setText(String.format("%.2f", grosswage.getHourly()));
        grossTotalHoursField.setText(String.format("%.0f", grosswage.getHours()));
        grossWageField.setText(String.format("%.2f", grosswage.getGross()));
    }
    
    private void printNet(){
        empNameField.setText(netwage.getEmpName());
        netSSSField.setText(String.format("%.2f", netwage.getSssData()));
        netPhilhealthField.setText(String.format("%.2f", netwage.getPhilhealthData()));
        netPagibigField.setText(String.format("%.2f", netwage.getPagibigData()));
        netLateField.setText(String.format("%.2f", netwage.getLateData()));
        netTotalDeductionsField.setText(String.format("%.2f", netwage.getTotalDeduction()));
        netTotalHoursField.setText(String.format("%.2f", grosswage.getHours()));
        netGrossField.setText(String.format("%.2f", grosswage.getGross()));
        netTaxableField.setText(String.format("%.2f", netwage.getTaxableIncome()));
        netWithholdingTaxField.setText(String.format("%.2f", netwage.getTax()));
        netWageField.setText(String.format("%.2f", netwage.getNet()));
    }
    
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);  // optional
        alert.setContentText(message);
        alert.showAndWait();
}
    
    private Integer inputValidation(String empID, String monthText) {
    // Check if Employee ID is missing
    if (empID == null || empID.trim().isEmpty()) {
        showAlert(Alert.AlertType.ERROR, "Missing Input", "Employee ID is required.");
        return null;
    }

    // Check if Employee ID is numeric
    try {
        Integer.parseInt(empID);
    } catch (NumberFormatException e) {
        showAlert(Alert.AlertType.ERROR, "Invalid Input", "Employee ID must be numeric.");
        return null;
    }

    // Validate Month
    try {
        int month = Integer.parseInt(monthText);
        if (month < 1 || month > 12) {
            showAlert(Alert.AlertType.ERROR, "Invalid Input", "Month must be between 1 and 12.");
            return null;
        }
        return month;
    } catch (NumberFormatException e) {
        showAlert(Alert.AlertType.ERROR, "Invalid Input", "Please enter a valid numeric month.");
        return null;
    }
}



}
