package com.mycompany.MotorPH;

import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.UIManager;

public class MotorPHMain {

    private static Scanner scanner = new Scanner(System.in);

    static ArrayList<AttendanceRecord> attendanceRecords = AttendanceRecord.getAttendanceRecords();

    public static void main(String[] args) {
        System.out.println("Current Working Directory: " + System.getProperty("user.dir"));
        System.out.println("Select mode:");
        System.out.println("1 - JavaFX");
        System.out.println("2 - Swing");

        String choice = scanner.nextLine();
        menu(choice, args);
        uiManager();
        
    }

    private static void menu(String detailSub, String[] args) {
        switch (detailSub) {
            case "1" -> runJavaFX(args);
            case "2" -> runSwing();
            default -> System.out.println("Invalid option. Please select 1 or 2.");
        }
    }

    private static void runJavaFX(String[] args) {
        javafx.application.Application.launch(MotorPHApp.class, args);
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
    
