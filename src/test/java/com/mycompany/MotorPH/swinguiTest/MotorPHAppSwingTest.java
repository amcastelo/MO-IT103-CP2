/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.MotorPH.swinguiTest;

import com.mycompany.MotorPH.swingui.MotorPHAppSwing;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Field;
/**
 *
 * @author Harvey Punsalan
 */
public class MotorPHAppSwingTest {
    private MotorPHAppSwing app;

    @BeforeEach
    public void setup() throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            app = new MotorPHAppSwing();
            app.setVisible(true);
        });

        Thread.sleep(500); // Allow GUI to settle
    }

    private Object getPrivateField(String fieldName) throws Exception {
        Field field = MotorPHAppSwing.class.getDeclaredField(fieldName);
        field.setAccessible(true);
        return field.get(app);
    }

    @Test
    public void testInitialPanelIsVisible() throws Exception {
        JPanel contentPanel = (JPanel) getPrivateField("contentPanel");

        Component visibleComponent = null;
        for (Component comp : contentPanel.getComponents()) {
            if (comp.isVisible()) {
                visibleComponent = comp;
                break;
            }
        }

        assertNotNull(visibleComponent, "No panel is visible by default.");
    }

    @Test
    public void testEmployeeButtonShowsEmployeePanel() throws Exception {
        JButton employeeButton = (JButton) getPrivateField("employeePageButton");
        JPanel contentPanel = (JPanel) getPrivateField("contentPanel");

        SwingUtilities.invokeLater(employeeButton::doClick);
        Thread.sleep(200);

        assertTrue(isPanelVisible(contentPanel, "EmployeePage"), "EmployeePage panel should be visible.");
    }

    @Test
    public void testPayrollButtonShowsPayrollPanel() throws Exception {
        JButton payrollButton = (JButton) getPrivateField("payrollPageButton");
        JPanel contentPanel = (JPanel) getPrivateField("contentPanel");

        SwingUtilities.invokeLater(payrollButton::doClick);
        Thread.sleep(200);

        assertTrue(isPanelVisible(contentPanel, "PayrollPage"), "PayrollPage panel should be visible.");
    }

    @Test
    public void testDashboardButtonShowsDashboardPanel() throws Exception {
        JButton dashboardButton = (JButton) getPrivateField("dashboardPageButton");
        JPanel contentPanel = (JPanel) getPrivateField("contentPanel");

        SwingUtilities.invokeLater(dashboardButton::doClick);
        Thread.sleep(200);

        assertTrue(isPanelVisible(contentPanel, "DashboardPage"), "DashboardPage panel should be visible.");
    }

    private boolean isPanelVisible(JPanel contentPanel, String name) {
        for (Component comp : contentPanel.getComponents()) {
            if (comp.isVisible()) {
                return true;
            }
        }
        return false;
    }
}


