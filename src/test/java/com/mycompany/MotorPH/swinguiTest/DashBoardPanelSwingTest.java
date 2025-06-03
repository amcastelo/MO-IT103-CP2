/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.MotorPH.swinguiTest;

import static org.junit.jupiter.api.Assertions.*;
import javax.swing.JLabel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mycompany.MotorPH.swingui.DashboardPanelSwing;


/**
 *
 * @author harvey punsalan
 */
public class DashBoardPanelSwingTest {

    private DashboardPanelSwing dashboardPanel;

    @BeforeEach
    public void setUp() {
        dashboardPanel = new DashboardPanelSwing();
    }

    @Test
    public void testPanelIsCreated() {
        assertNotNull(dashboardPanel);
    }

    @Test
    public void testLabelExistsWithCorrectText() {
        // Access the label by name using reflection since it's private
        JLabel label = (JLabel) TestUtils.getChildNamed(dashboardPanel, "jLabel1");
        assertNotNull(label, "Label jLabel1 should exist");
        assertEquals("MotorPH", label.getText());
    }
}
