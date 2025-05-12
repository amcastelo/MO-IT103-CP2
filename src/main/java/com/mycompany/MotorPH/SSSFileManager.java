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

/**
 *
 * @author adamm
 */
public class SSSFileManager implements FileLoader{
    
    private static final String TXT_FILE_PATH = "src/main/resources/SSSCont1.txt";
    
    // LOADS THE SSS CONTRIBUTION FILE AND SAVES IT AS NEW OBJECT IN OBJECT ARRAY LIST
    @Override
    public List<SSS> loadFile() {
        List<SSS> deductionRecord = new ArrayList<>();
        
        // Tries to read the file and load data from it before closing.
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(TXT_FILE_PATH))) {
            bufferedReader.readLine();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                // Assuming the format of your file is: compensationRange,contribution
                String[] record = line.split(",");
                String compensationRange = record[0];
                double contribution = Double.parseDouble(record[1]);

                SSS deductionRecordItem = new SSS(compensationRange, contribution);
                deductionRecord.add(deductionRecordItem);
            }
        } catch (IOException e) {
            handleException(e);
        }
        
        return deductionRecord;
    }    
    
    private static void handleException(Exception e) {
            e.printStackTrace();    
        }
    
}
