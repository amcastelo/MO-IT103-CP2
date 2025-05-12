/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.MotorPH;

import java.util.List;

/**
 *
 * @author adamm
 * @param <T>
 */
public interface FileLoader<T> {
    List<T> loadFile();
}
