/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.MotorPH;

// This SSS class extends Calculation and calculates the SSS deduction for an employee

import java.util.List;

public class SSS extends Calculation {
    
    
    private String compensationRange;
    private double contribution;
    
    private static final List<SSS> sssDeductionRecords;
    
    private static double sssDeduction;
    
    // CONSTRUCTOR
    public SSS(String compensationRange, double contribution) {
        this.compensationRange = compensationRange;
        this.contribution = contribution;
    }
    
    public SSS(){}
    
    // INITIALIZE
    static {
        SSSFileManager sssFile = new SSSFileManager();
        sssDeductionRecords = sssFile.loadFile();
    }
    
    @Override
    public double calculate(){
        double gross = Grosswage.gross;
        
        // Iterates through every compensation range to get the proper contribution.
        for (SSS record : sssDeductionRecords) {
            double[] range = parseSssCompensationRange(record.getCompensationRange());
                if (gross > range[0] && gross <= range[1]) {

                    sssDeduction = record.getContribution();
                    break;  // Assuming that only one range should match, you can modify as needed
                }
            }
        return sssDeduction;
    }
    
        // PARSES SSS CONTRIBUTION RANGE .CSV FILE TO USE IN SSS CALCULATION
    private static double[] parseSssCompensationRange(String compensationRange) {
    // Remove any extra spaces
    compensationRange = compensationRange.trim();

    // Split the range by hyphen
    String[] rangeParts = compensationRange.split("-");
    
    // Checks if the compensation range is in the correct format.
    if (rangeParts.length != 2) {
        throw new IllegalArgumentException("Invalid compensation range format: " + compensationRange);
    }

    try {
        double start = Double.parseDouble(rangeParts[0].trim());
        double end = Double.parseDouble(rangeParts[1].trim());

        return new double[]{start, end};
    } catch (NumberFormatException e) {
        throw new IllegalArgumentException("Invalid numeric format in compensation range: " + compensationRange, e);
    }
} 

    /**
     * @return the compensationRange
     */
    public String getCompensationRange() {
        return compensationRange;
    }

    /**
     * @return the contribution
     */
    public double getContribution() {
        return contribution;
    }

    /**
     * @return the sssDeduction
     */
    public static double getSssDeduction() {
        return sssDeduction;
    }
    
}
