package com.mycompany.MotorPH;

import com.mycompany.MotorPH.swingui.LoginPage;
import javax.swing.UIManager;

public class MotorPHMain {

    public static void main(String[] args) {
        // Set the UI Look and Feel first
        uiManager();
        
        // To run the Login Page 
        runLoginPage();
    }
    
    private static void runLoginPage() {
        java.awt.EventQueue.invokeLater(() -> {
            new LoginPage().setVisible(true);
        });
    }
    
    private static void uiManager(){
        try {
            // System LaF
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

            // OR FlatLaf (uncomment if you want to use FlatLaf)
            // UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}   
    
