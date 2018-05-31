package com.company.miniFrame;

import javax.swing.*;

public class ViewMiniFrame implements ViewMini {
    private ControllerMini controllerMini;

    private JFrame frame;
    
    private JTextField titleTextfield;

    @Override
    public void setController(ControllerMini controllerMini) {
        this.controllerMini = controllerMini;
    }

    @Override
    public void create() {
        frame = new JFrame();
        frame.setSize(400, 200);
        frame.setLayout(null);
        
        titleTextfield = new JTextField();
        titleTextfield.setBounds(10, 50, 300, 30);
        frame.add(titleTextfield);
        
        JButton okButton = new JButton("Ok");
        okButton.setBounds(10, 100, 100, 30);
        okButton.addActionListener(e -> controllerMini.handleOkButtonClick());
        frame.add(okButton);
        
        frame.repaint();
        frame.revalidate();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    @Override
    public void close() {
        frame.dispose();
    }

    @Override
    public String getTitleText() {
        return titleTextfield.getText();
    }
}
