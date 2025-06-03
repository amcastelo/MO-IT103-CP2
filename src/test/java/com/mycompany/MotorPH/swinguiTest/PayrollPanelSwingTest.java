/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.MotorPH.swinguiTest;

import com.mycompany.MotorPH.swingui.PayrollPanelSwing;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javax.swing.*;
import java.lang.reflect.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Harvey Punsalan
 */
public class PayrollPanelSwingTest {
    private PayrollPanelSwing payrollPanel;
    
    @BeforeEach
    public void setUp() {
        payrollPanel = new PayrollPanelSwing();
    }
    
    @Test
    public void testTabbedPaneSetup() throws Exception {
        // Check if the main tabbed pane has both tabs for gross and net calculations
        JTabbedPane tabbedPane = getField("jTabbedPane1", JTabbedPane.class);
        assertNotNull(tabbedPane);
        assertEquals(2, tabbedPane.getTabCount());
        assertEquals("Gross wage", tabbedPane.getTitleAt(0));
        assertEquals("Net wage", tabbedPane.getTitleAt(1));
    }
    
    @Test
    public void testCalculateButton() throws Exception {
        // Make sure the calculate button exists with correct label
        JButton calculateButton = getField("calculateButton", JButton.class);
        assertNotNull(calculateButton);
        assertEquals("Calculate", calculateButton.getText());
    }
    
    @Test
    public void testEmployeeFields() throws Exception {
        // Test the input fields for employee data
        JTextField empIDField = getField("empIDField", JTextField.class);
        JTextField empNameField = getField("empNameField", JTextField.class);
        JTextField monthField = getField("monthField", JTextField.class);
        
        assertNotNull(empIDField);
        assertNotNull(empNameField);
        assertNotNull(monthField);
        
        // Employee name should be read-only since it's populated automatically
        assertFalse(empNameField.isEnabled());
    }
    
    @Test
    public void testGrossWageFields() throws Exception {
        // Check gross wage calculation fields are present
        JTextField grossHourlyField = getField("grossHourlyField", JTextField.class);
        JTextField grossTotalHoursField = getField("grossTotalHoursField", JTextField.class);
        JTextField grossWageField = getField("grossWageField", JTextField.class);
        
        assertNotNull(grossHourlyField);
        assertNotNull(grossTotalHoursField);
        assertNotNull(grossWageField);
        
        // All gross fields should be disabled since they show calculated results
        assertFalse(grossHourlyField.isEnabled());
        assertFalse(grossTotalHoursField.isEnabled());
        assertFalse(grossWageField.isEnabled());
    }
    
    @Test
    public void testNetWageFields() throws Exception {
        // Verify net wage calculation fields exist
        JTextField netSSSField = getField("netSSSField", JTextField.class);
        JTextField netPhilhealthField = getField("netPhilhealthField", JTextField.class);
        JTextField netPagibigField = getField("netPagibigField", JTextField.class);
        JTextField netWageField = getField("netWageField", JTextField.class);
        
        assertNotNull(netSSSField);
        assertNotNull(netPhilhealthField);
        assertNotNull(netPagibigField);
        assertNotNull(netWageField);
        
        // Net calculation fields should be read-only display fields
        assertFalse(netSSSField.isEnabled());
        assertFalse(netPhilhealthField.isEnabled());
        assertFalse(netPagibigField.isEnabled());
        assertFalse(netWageField.isEnabled());
    }
    
    @Test
    public void testImageView() throws Exception {
        // Check if employee image placeholder is initialized
        JLabel imageView = getField("imageView", JLabel.class);
        assertNotNull(imageView);
    }
    
    @Test
    public void testValidation() throws Exception {
        // Test the input validation logic with different scenarios
        Method validation = PayrollPanelSwing.class.getDeclaredMethod("inputValidation", String.class, String.class);
        assertNotNull(validation);
        validation.setAccessible(true);
        
        // Test with valid employee ID and month
        Integer result = (Integer) validation.invoke(payrollPanel, "123", "5");
        assertEquals(5, result);
        
        // Test with non-numeric employee ID - should fail
        Integer badEmpId = (Integer) validation.invoke(payrollPanel, "abc", "5");
        assertNull(badEmpId);
        
        // Test with month out of range - should fail
        Integer badMonth = (Integer) validation.invoke(payrollPanel, "123", "15");
        assertNull(badMonth);
    }
    
    @Test
    public void testPrintMethods() throws Exception {
        // Test that the display methods exist and can be called
        Method printGross = PayrollPanelSwing.class.getDeclaredMethod("printGross");
        Method printNet = PayrollPanelSwing.class.getDeclaredMethod("printNet");
        
        assertNotNull(printGross);
        assertNotNull(printNet);
        
        printGross.setAccessible(true);
        printNet.setAccessible(true);
        
        // These methods should execute without throwing exceptions
        assertDoesNotThrow(() -> printGross.invoke(payrollPanel));
        assertDoesNotThrow(() -> printNet.invoke(payrollPanel));
    }
    
    // helper method to get private fields
    private <T> T getField(String fieldName, Class<T> fieldType) throws Exception {
        Field field = PayrollPanelSwing.class.getDeclaredField(fieldName);
        field.setAccessible(true);
        return fieldType.cast(field.get(payrollPanel));
    }
}
