package com.mycompany.MotorPH;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class EmployeeModelFromFileTest {

    @BeforeEach
    void setUp() {
        // You can perform any setup operations here if needed
    }

    @Test
    void testLoadEmployees() {
        // Prepare test data
        String testData = "123,Doe,John,1990-01-01,123 Street,123456789,SSS123,PH123,TIN123,PAG123,Active,Manager,Supervisor,50000,2000,1000,500,50000,10.5\n";
        // Add more test data if needed

        // Create a temporary test file
        File tempFile = createTempFile(testData);

        // Set the test file path to the temporary file
        String testFilePath = tempFile.getAbsolutePath();
        EmployeeFileManager.setEmployeeFilePath(testFilePath);

        // Load employees
        List<Employee> employees = EmployeeFileManager.loadEmployees();

        // Assertions
        assertEquals(1, employees.size()); // Assuming one employee is loaded
        Employee employee = employees.get(0);
        assertEquals("123", employee.getEmployeeNumber());
        assertEquals("Doe", employee.getLastName());
        // Add more assertions for other fields if needed

        // Clean up: Delete the temporary test file
        tempFile.delete();
    }

    // Helper method to create a temporary test file
    private File createTempFile(String testData) {
        try {
            File tempFile = File.createTempFile("tempData", ".txt");
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
            writer.write(testData);
            writer.close();
            return tempFile;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Add more test methods as needed
}
