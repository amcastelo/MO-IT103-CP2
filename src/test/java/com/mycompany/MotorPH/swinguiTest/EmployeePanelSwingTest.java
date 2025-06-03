/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.MotorPH.swinguiTest;

import com.mycompany.MotorPH.swingui.EmployeePanelSwing;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author harvey punsalan
 */
public class EmployeePanelSwingTest {
    private EmployeePanelSwing employeePanel;

    @BeforeEach
    public void setUp() {
        // Create a fresh instance of the Employee panel before each test
        employeePanel = new EmployeePanelSwing();
    }

    @Test
    public void testEmpTableInitialized() throws Exception {
        // Make sure the JTable inside the panel is set up correctly
        JTable empTable = getPrivateField("empTable", JTable.class);
        assertNotNull(empTable.getModel(), "Table model shouldn't be null — it should have data or at least column setup");
        assertTrue(empTable.getColumnCount() > 0, "Table should have some columns loaded by default");
    }

    @Test
    public void testReloadButtonExists() throws Exception {
        // Check if the Reload button exists and displays the right text
        JButton reloadButton = getPrivateField("ReloadButton", JButton.class);
        assertNotNull(reloadButton, "Reload button should be created in the panel");
        assertEquals("Reload", reloadButton.getText(), "Reload button should be labeled correctly");
    }

    @Test
    public void testSearchFieldExists() throws Exception {
        // Confirm that the search field is properly initialized
        JTextField searchField = getPrivateField("searchField", JTextField.class);
        assertNotNull(searchField, "Search field should be present in the panel");
    }

    /**
     * Utility method to retrieve private fields from the EmployeePanelSwing class using reflection.
     * This avoids needing to make the fields public or write getter just for testing.
     */
    private <T> T getPrivateField(String fieldName, Class<T> fieldType) throws Exception {
        Field field = EmployeePanelSwing.class.getDeclaredField(fieldName);
        field.setAccessible(true); // Bypass private access
        return fieldType.cast(field.get(employeePanel));
    }
}

