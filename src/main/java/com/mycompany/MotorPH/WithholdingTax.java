        /*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
     */
    package com.mycompany.MotorPH;

    // This WithholdingTax class extends Calculation and calculates the withholding tax for an employee
    public class WithholdingTax {
        public static double tax, taxableIncome, afterTax;
        
        public double calculate(double gross, String empID, int month, double hourly){
            // Initialize other deductions to call their calculate() method.
            DeductionCalculation sss = new SSS();
            DeductionCalculation philhealth = new Philhealth();
            DeductionCalculation pagibig = new Pagibig();           
            LatePenalty latePenalty = new LatePenalty();
            
            // Declare a temporary variable storing the total deduction.
            double totalDeduction = sss.calculate(gross) + philhealth.calculate(gross) + pagibig.calculate(gross) + latePenalty.calculate(empID, month, hourly);
            
            // Compute taxable income by getting value of gross wage minus totalDeduction.
            taxableIncome = gross - totalDeduction;
            
            // Conditional statement to determine tax rate.
            if (taxableIncome <= 20832) {
                tax = 0;

            } else if (taxableIncome > 20832 && taxableIncome <= 33333) {
                tax = (taxableIncome - 20832) * 0.20;

            } else if (taxableIncome > 33333 && taxableIncome <= 66667) {
                tax = 2500 + (taxableIncome - 33333) * 0.25;

            } else if (taxableIncome > 66667 && taxableIncome <= 166667) {
                tax = 10833 + (taxableIncome - 66667) * 0.30;

            } else if (taxableIncome > 166667 && taxableIncome <= 666667) {
                tax = 40833.33 + (taxableIncome - 166667) * 0.32;

            } else {
                tax = 200833.33 + (taxableIncome - 666667) * 0.35;
            }
            
            afterTax = taxableIncome - tax;
            
            // returns net.
        return afterTax;
        }
    }
