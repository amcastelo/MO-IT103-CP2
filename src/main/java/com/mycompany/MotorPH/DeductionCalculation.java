/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.MotorPH;

import java.text.DecimalFormat;

/**
 *
 * @author Isaac
 */
public interface DeductionCalculation {
    
    DecimalFormat decimalFormat = new DecimalFormat("#.##");

    default double calculate(double gross){
        return 0;
    };
    
}
