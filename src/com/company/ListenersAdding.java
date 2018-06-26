package com.company;

import javax.swing.*;

public class ListenersAdding {
    public static void addKeyListener(JTextField from, JTextField to) {
        from.addKeyListener(new TextfieldKeyListener(from, to));
    }
    
    public static void addMouseListener(JTextField textField) {
        textField.addMouseListener(new TextfieldMouseListener(textField));
    }
}
