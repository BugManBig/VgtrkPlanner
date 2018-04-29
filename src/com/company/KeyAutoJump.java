package com.company;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyAutoJump implements KeyListener {
    private JTextField from;
    private JTextField to;

    public KeyAutoJump(JTextField from, JTextField to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (from.getText().length() == 2) {
            to.requestFocus();
        }
    }
}
