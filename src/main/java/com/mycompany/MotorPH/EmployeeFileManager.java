/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.MotorPH;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

// This EmployeeFileManager class is responsible for loading Employee data from a text file
    public class EmployeeFileManager implements FileLoader{
        // Path to the text files containing Employee data
        private static String employeeFilePath = "src/main/resources/Data.txt";
        private static final String removedFilePath = "src/main/resources/removedEmployees.txt";
        
        private static final List<Employee> employees;
        
        private static List<List<String>> cachedCSVData = new ArrayList<>();
        private static String[] cachedHeaders;
        
    // INITIALIZE
    static {
            EmployeeFileManager empFile = new EmployeeFileManager();
            employees = empFile.loadFile();
    }
    
    // LOADS EMPLOYEE DATA
    @Override
    public List<Employee> loadFile() {
    List<Employee> employees = new ArrayList<>();
    cachedCSVData.clear();      // Clear previous cache
    cachedHeaders = null;       // Reset cached headers

    try (BufferedReader reader = new BufferedReader(new FileReader(employeeFilePath))) {
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

                // Add to cached list instead of ObservableList
                cachedCSVData.add(new ArrayList<>(employeeData));
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }

    return employees;
}

    // LOADS CACHED DATA TO TABLE
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
    
    public void loadSelectedIdentifiersToSwing(JTable table) {
        // These are the indexes of the columns you want to keep
        List<Integer> selectedIndexes = Arrays.asList(0, 1, 2, 6, 7, 8, 9);

        // Ensure the file is loaded
        loadFile();

        // Build new table model
        DefaultTableModel model = new DefaultTableModel();

        // Add column headers using the selected indexes
        for (int index : selectedIndexes) {
            model.addColumn(cachedHeaders[index]); // since cachedHeaders is a String[]
        }

        // Add rows with only the selected columns
        for (List<String> row : cachedCSVData) {
            List<String> filteredRow = new ArrayList<>();
            for (int index : selectedIndexes) {
                filteredRow.add(row.get(index));
            }
            model.addRow(filteredRow.toArray());
        }

        // Set the model to the table
        table.setModel(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
    }


    // DELETES RECORD FROM TABLE, CACHE AND CSV FILE
    public static void deleteRecord(String empID){
        String tempFilePath = "src/main/resources/temp.txt";
        String idToDelete = empID.trim();

        if (idToDelete.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter an Employee ID.");
            return;
        }

        boolean recordDeleted = false;

        try (
            BufferedReader reader = new BufferedReader(new FileReader(employeeFilePath));
            BufferedWriter tempWriter = new BufferedWriter(new FileWriter(tempFilePath));
            BufferedWriter removedWriter = new BufferedWriter(new FileWriter(removedFilePath, true))
        ) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length > 0 && parts[0].equals(idToDelete)) {
                    removedWriter.write(line);
                    removedWriter.newLine();
                    recordDeleted = true;
                    continue;
                }
                tempWriter.write(line);
                tempWriter.newLine();
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error processing file: " + e.getMessage());
            e.printStackTrace();
            return;
        }

        // Now try deleting and renaming AFTER streams are closed
        if (!recordDeleted) {
            new File(tempFilePath).delete();
            JOptionPane.showMessageDialog(null, "Record not found.");
            return;
        }

        File originalFile = new File(employeeFilePath);
        File tempFile = new File(tempFilePath);

        // Debug
        System.out.println("Original exists: " + originalFile.exists());
        System.out.println("Temp exists: " + tempFile.exists());

        boolean deleted = originalFile.delete();
        System.out.println("Deleted original: " + deleted);

        boolean renamed = tempFile.renameTo(originalFile);
        System.out.println("Renamed temp: " + renamed);

        if (!deleted || !renamed) {
            JOptionPane.showMessageDialog(null, "Error updating file.");
            return;
        }

        JOptionPane.showMessageDialog(null, "Record deleted and moved to removedEmployees.csv");
    }
    
    // UPDATES RECORD ON TABLE, CACHE AND CSV FILE
    public static void updateRecord(String[] empData) {
    String idToUpdate = empData[0].trim();
    String tempFilePath = "src/main/resources/temp.txt";

    try (
        BufferedReader reader = new BufferedReader(new FileReader(employeeFilePath));
        BufferedWriter tempWriter = new BufferedWriter(new FileWriter(tempFilePath))
    ) {
        String line;
        boolean recordUpdated = false;

        while ((line = reader.readLine()) != null) {
            String[] data = line.split(",");
            if (data.length > 0 && data[0].trim().equals(idToUpdate)) {
                // Build updated line using empData array
                String updatedLine = String.join(",",
                    empData[0].trim(),  // ID
                    empData[1].trim(),  // Last Name
                    empData[2].trim(),  // First Name
                    empData[3].trim(),  // Birthday
                    empData[4].trim(),  // Address
                    empData[5].trim(),  // Phone Number
                    empData[6].trim(),  // SSS
                    empData[7].trim(),  // Philhealth
                    empData[8].trim(),  // TIN
                    empData[9].trim(),  // Pagibig
                    empData[10].trim(), // Status
                    empData[11].trim(), // Position
                    empData[12].trim(), // Supervisor
                    empData[13].trim(), // Basic Salary
                    empData[14].trim(), // Rice Subsidy
                    empData[15].trim(), // Phone Allowance
                    empData[16].trim(), // Clothing Allowance
                    empData[17].trim(), // Gross Semi
                    empData[18].trim()  // Hourly Rate
                );

                tempWriter.write(updatedLine);
                tempWriter.newLine();
                recordUpdated = true;
            } else {
                tempWriter.write(line);
                tempWriter.newLine();
            }
        }

    } catch (IOException e) {
        JOptionPane.showMessageDialog(null, "Error processing file: " + e.getMessage());
        e.printStackTrace();
        return;
    }

    // Replace original file with updated temp file
    File originalFile = new File(employeeFilePath);
    File tempFile = new File(tempFilePath);

    boolean deleted = originalFile.delete();
    boolean renamed = tempFile.renameTo(originalFile);

    if (!deleted || !renamed) {
        JOptionPane.showMessageDialog(null, "Error updating file.");
        return;
    }

    JOptionPane.showMessageDialog(null, "Record successfully updated.");
}

    
    // USED FOR SORTING TABLE ON SEARCH
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

    
    // USED FOR FILTERING BY EMPLOYEE ID
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
        return List.copyOf(employees);
    }

}
