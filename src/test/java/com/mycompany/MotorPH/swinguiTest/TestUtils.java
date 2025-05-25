/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.MotorPH.swinguiTest;

import java.awt.Component;
import java.awt.Container;
import java.lang.reflect.Field;

/**
 *
 * @author harvey punsalan
 */
public class TestUtils {

    public static Component getChildNamed(Component parent, String name) {
        if (name.equals(parent.getName())) {
            return parent;
        }
        if (parent instanceof Container) {
            Component[] children = ((Container) parent).getComponents();
            for (Component c : children) {
                Component child = getChildNamed(c, name);
                if (child != null) {
                    return child;
                }
            }
        }
        // Also try reflection to get private fields by name (if name is the variable name)
        try {
            Field field = parent.getClass().getDeclaredField(name);
            field.setAccessible(true);
            Object obj = field.get(parent);
            if (obj instanceof Component) {
                return (Component) obj;
            }
        } catch (Exception e) {
            // ignore
        }
        return null;
    }
}