/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.MotorPH;

/**
 *
 * @author Isaac
 */
public class Netwage {

    private double sssData;
    private double philhealthData;
    private double pagibigData;
    private double lateData;
    private double totalDeduction;
    private double net;
    private double taxableIncome;
    private double tax;
    private String empName;
    private Grosswage grosswage = new Grosswage();   
    
    public double calculate(String empID, int month){ 
        DeductionCalculation sss = new SSS();
        DeductionCalculation philhealth = new Philhealth();
        DeductionCalculation pagibig = new Pagibig();
        
        WithholdingTax withholdingTax = new WithholdingTax();
        LatePenalty latePenalty = new LatePenalty();
        
        //Call grosswage calculation to prepare necessary values.
        grosswage.calculate(empID, month);
        
        //Call the calculate() method of each class and assign their values to temporary variables.
        sssData = sss.calculate(grosswage.getGross());
        philhealthData = philhealth.calculate(grosswage.getGross());
        pagibigData = pagibig.calculate(grosswage.getGross());
        lateData = latePenalty.calculate(grosswage.getTargetEmployeeID(), grosswage.getTargetMonth(), grosswage.getHourly());
        totalDeduction = getSssData() + getPhilhealthData() + getPagibigData() + getLateData();
        empName = grosswage.getEmployeeName();
        
        net = withholdingTax.calculate(grosswage.getGross(), grosswage.getTargetEmployeeID(), grosswage.getTargetMonth(), grosswage.getHourly());      
        //gets the value of taxableIncome and tax to be used for printing.
        taxableIncome = WithholdingTax.taxableIncome;
        tax = WithholdingTax.tax;

        return getNet();  // Return the net wage
    }
        
        /**
     * @return the sssData
     */
    public double getSssData() {
        return sssData;
    }

    /**
     * @return the philhealthData
     */
    public double getPhilhealthData() {
        return philhealthData;
    }

    /**
     * @return the pagibigData
     */
    public double getPagibigData() {
        return pagibigData;
    }

    /**
     * @return the lateData
     */
    public double getLateData() {
        return lateData;
    }

    /**
     * @return the totalDeduction
     */
    public double getTotalDeduction() {
        return totalDeduction;
    }

    /**
     * @return the net
     */
    public double getNet() {
        return net;
    }

    /**
     * @return the taxableIncome
     */
    public double getTaxableIncome() {
        return taxableIncome;
    }

    /**
     * @return the tax
     */
    public double getTax() {
        return tax;
    }
    
    public String getEmpName() {
        return empName;
    }
    
    
}
