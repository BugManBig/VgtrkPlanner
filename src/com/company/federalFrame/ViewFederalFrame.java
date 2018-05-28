package com.company.federalFrame;

import javax.swing.*;

public class ViewFederalFrame implements ViewFederal {
    private ControllerFederal controllerFederal;

    private JFrame frame;
    
    private JTextField titleTextfield;

    @Override
    public void setController(ControllerFederal controllerFederal) {
        this.controllerFederal = controllerFederal;
    }

    @Override
    public void create() {
        frame = new JFrame();
        frame.setSize(400, 200);
        frame.setLayout(null);
        
        titleTextfield = new JTextField();
        titleTextfield.setBounds(10, 50, 300, 30);
        frame.add(titleTextfield);
        
        frame.repaint();
        frame.revalidate();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
