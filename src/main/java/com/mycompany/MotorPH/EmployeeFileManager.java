/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.MotorPH;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

// This EmployeeFileManager class is responsible for loading Employee data from a text file
    public class EmployeeFileManager implements FileLoader{
        // Path to the text file containing Employee data
        private static String TXT_FILE_PATH = "src/main/resources/Data.txt";
        private static final List<Employee> employees;
        
        public static List<List<String>> cachedCSVData = new ArrayList<>();
        public static String[] cachedHeaders;
        

        
    //INITIALIZE
    static {
            EmployeeFileManager empFile = new EmployeeFileManager();
            employees = empFile.loadFile();
    }
    
    //LOADS EMPLOYEE DATA
    @Override
    public List<Employee> loadFile() {
    List<Employee> employees = new ArrayList<>();
    cachedCSVData.clear();      // Clear previous cache
    cachedHeaders = null;       // Reset cached headers

    try (BufferedReader reader = new BufferedReader(new FileReader(TXT_FILE_PATH))) {
        String headerLine = reader.readLine();
        if (headerLine != null) {
            cachedHeaders = headerLine.split(","); // Cache headers from the first line
        }

        String currentLine;
        while ((currentLine = reader.readLine()) != null) {
            List<String> employeeData = parseTxtLine(currentLine);
            if (employeeData.size() >= 19) {
                Employee employee = new Employee(employeeData.toArray(new String[0]));
                employees.add(employee);

                // Cache the data as regular List
                cachedCSVData.add(new ArrayList<>(employeeData));
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }

    return employees;
}
    ///
    public static void loadCachedSwing(JTable table) {
        if (cachedHeaders == null || cachedCSVData.isEmpty()) return;

        // Create a DefaultTableModel with cached headers as column names
        DefaultTableModel model = new DefaultTableModel(cachedHeaders, 0);

        // Add each row of data from cachedCSVData
        for (List<String> row : cachedCSVData) {
            model.addRow(row.toArray(new String[0]));
        }

        // Set model to the JTable
        table.setModel(model);
    }
    
    public static TableRowSorter<DefaultTableModel> searchSort(JTable table) {
        if (cachedHeaders == null || cachedCSVData.isEmpty()) return null;

        DefaultTableModel model = new DefaultTableModel(cachedHeaders, 0);
        for (List<String> row : cachedCSVData) {
            model.addRow(row.toArray(new String[0]));
        }

        table.setModel(model);
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);
        return sorter;
    }
    
    public static void filterByEmployeeID(JTable table, TableRowSorter<DefaultTableModel> sorter, String employeeID) {
        if (employeeID.trim().isEmpty()) {
            sorter.setRowFilter(null); // Show all if empty
        } else {
            // Only filter the first column (index 0) assuming it's Employee ID
            sorter.setRowFilter(RowFilter.regexFilter("^" + employeeID + "$", 0));
        }
    }
    
    //PARSE STRING SURROUNDED WITH QUOTATION MARKS WITH COMMAS INSIDE IT
    private static List<String> parseTxtLine(String line) {
        List<String> tokens = new ArrayList<>();
        boolean inQuotes = false;
        StringBuilder buffer = new StringBuilder();
        for (char c : line.toCharArray()) {
            if (c == '"') {
                inQuotes = !inQuotes; // Toggle the inQuotes flag
            } else if (c == ',' && !inQuotes) {
                // End of a token
                tokens.add(buffer.toString());
                buffer = new StringBuilder(); // Reset buffer
            } else {
                buffer.append(c);
            }
        }
        // Add the last token, if any
        if (buffer.length() > 0) {
            tokens.add(buffer.toString());
        }
        return tokens;
    }      

    /**
     * @return the employees
     */
    public static List<Employee> getEmployeeModelList(){
        return employees;
    }

    /** Set the path to the text file containing Employee data
     *  @param aTXT_FILE_PATH the TXT_FILE_PATH to set
     */
    public static void setTXT_FILE_PATH(String aTXT_FILE_PATH) {
        TXT_FILE_PATH = aTXT_FILE_PATH;
    }
}