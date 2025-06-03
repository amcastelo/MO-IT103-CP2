package com.mycompany.MotorPH;

import com.mycompany.MotorPH.swingui.MotorPHAppSwing;
import javax.swing.UIManager;

public class MotorPHMain {

    public static void main(String[] args) {
        runSwing();
        uiManager();
    }
    private static void runSwing() {
        java.awt.EventQueue.invokeLater(() -> new MotorPHAppSwing().setVisible(true));
    }
    
    private static void uiManager(){
        try {
            // System LaF
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

            // OR FlatLaf
            // UIManager.setLookAndFeel(new FlatLightLaf());
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
}   
    
