package com.company;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TextfieldKeyListener implements KeyListener {
    private JTextField textFieldFrom;
    private JTextField textFieldTo;

    public TextfieldKeyListener(JTextField textFieldFrom, JTextField textFieldTo) {
        this.textFieldFrom = textFieldFrom;
        this.textFieldTo = textFieldTo;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (textFieldFrom.getText().length() == 2) {
            textFieldTo.requestFocus();
        }
        
    }
}
