/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.MotorPH;

// Pagibig class extends Calculation and calculates the Pagibig deduction for an employee
public class Pagibig implements DeductionCalculation {
    private static double pagibigDeduction;
    
    @Override
    public double calculate(double gross){
        double pagibig;

        // Conditional statement to calculate the Pagibig deduction based on gross wage range
        if (gross > 1000.00 && gross <= 1500.00) {
            pagibig = gross * 0.03;
        } else {
            pagibig = gross * 0.04;     
        }
        
        // Maximum amount must not exceed 100.
        if (pagibig > 100) {
            pagibig = 100;
        }
        // To store the Pagibig deduction value and return it
        return pagibigDeduction = pagibig;
    }
    
}
