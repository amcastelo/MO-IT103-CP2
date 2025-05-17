/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.MotorPH;
import java.text.DecimalFormat;
import java.util.List;

/**
 *
 * @author Isaac
 */
public class Grosswage {

    private String targetEmployeeID;
    private String employeeName;
    private String employeeID;
    private int targetMonth;
    private double gross;
    private double hourly;
    private double hours;  
    DecimalFormat decimalFormat = new DecimalFormat("#.##");
    
    public double calculate(String employeeID, int month){
        List<Employee> employees = EmployeeFileManager.getEmployeeModelList();  // Get the list of employees
        
            targetEmployeeID = employeeID;
            targetMonth = month;

            for (Employee employee : employees) {
                if (employee.getEmployeeNumber().equals(getTargetEmployeeID())) {
                    // Assuming the employee ID is in the first column, and hourly rate is in the last column
                    // setHourlyRate(employee.getHourlyRate());
                    setHourly(employee.getHourlyRate());
                    // employeeName = employee.getFirstName() +" " + employee.getLastName();
                    // Remove commas from the hourly rate string
                    setHourly(getHourly());
                    employeeID = employee.getEmployeeNumber();
                    // Check if the hourly rate string is a valid decimal number
                    if (isValidDecimal(Double.toString(getHourly()))) {
                        double HourlyRate = employee.getHourlyRate();
                        long hour = AttendanceRecord.calculateTotalHoursAndPrint(2022, getTargetMonth(), getTargetEmployeeID());
                        double hoursCalculated = HourlyRate * hour;
                        
                        setHourly(HourlyRate);
                        hours = hour;
                        gross = hoursCalculated;
                        employeeName =  employee.getLastName() + ", " +employee.getFirstName();
                        //printGross(employee.getEmployeeNumber(), employeeName, HourlyRate, hour, gross);
                    } else {
                        System.out.println("Invalid hourly rate for Employee ID " + getEmployeeID() + ": " + getHourly());
                        System.out.println(); // Move to the next line for the next row
                    }                                              
                        
                    return getGross(); // Exit the loop once the employee is found
                }                               
            }
            
            // If the loop completes without finding the employee ID
            System.out.println("Employee ID " + getEmployeeID() + " not found.");
        return getGross();
    }
     
    public void printGross(){
        System.out.println("""
                ------------------------------------------           
                Employee ID: %s
                Name: %s
                Hourly Rate: $%.2f
                Total Hours: %s
                Gross Wage: $%s
                ------------------------------------------
                """.formatted(getEmployeeID(), getEmployeeName(), getHourly(), 
                    getHours()
                , decimalFormat.format(getGross())));
    }
    
    //CHECKS IF DECIMAL IN HOURLY RATE IS VALID
    private boolean isValidDecimal(String str) {
        try {
        Double.parseDouble(str);
        return true;
    } catch (NumberFormatException e) {
        return false;
    }
}

    /**
     * @return the targetEmployeeID
     */
    public String getTargetEmployeeID() {
        return targetEmployeeID;
    }

    /**
     * @return the targetMonth
     */
    public int getTargetMonth() {
        return targetMonth;
    }

    /**
     * @return the employeeID
     */
    public String getEmployeeID() {
        return employeeID;
    }

    /**
     * @return the hourly
     */
    public double getHourly() {
        return hourly;
    }

    /**
     * @param aHourly the hourly to set
     */
    public void setHourly(double aHourly) {
        hourly = aHourly;
    }

    /**
     * @return the employeeName
     */
    public String getEmployeeName() {
        return employeeName;
    }

    /**
     * @return the gross
     */
    public double getGross() {
        return gross;
    }

    /**
     * @return the hours
     */
    public double getHours() {
        return hours;
    }
}
