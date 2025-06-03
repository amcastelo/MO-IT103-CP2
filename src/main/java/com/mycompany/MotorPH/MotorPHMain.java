package com.mycompany.MotorPH;

import com.mycompany.MotorPH.swingui.MotorPHAppSwing;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.UIManager;

public class MotorPHMain {

    private static Scanner scanner = new Scanner(System.in);

    static ArrayList<AttendanceRecord> attendanceRecords = AttendanceRecord.getAttendanceRecords();

    public static void main(String[] args) {
        System.out.println("Current Working Directory: " + System.getProperty("user.dir"));
        
        // Set UI Manager before launching the application
        uiManager();
        
        // Launch Swing application directly
        runSwing();
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

    
