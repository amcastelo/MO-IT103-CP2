/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.MotorPH.fxUI;

import com.mycompany.MotorPH.fxUI.MainPageController;

/**
 *
 * @author adamm
 */

/**
 * Implemented by FXML controllers that need access to MainPageController.
 */
public interface MainControllerAware {
    void setMainController(MainPageController mainPageController);
}
