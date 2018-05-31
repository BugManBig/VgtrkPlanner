package com.company.miniFrame;

import com.company.Chrono;

import javax.swing.*;

public class ViewMiniFrame implements ViewMini {
    private ControllerMini controllerMini;

    private JFrame frame;
    
    private static final int FRAME_WIDTH = 400;
    private static final int FRAME_HEIGHT = 200;
    
    private JTextField titleTextfield;
    
    private JTextField startHrsTextfield;
    private JTextField startMinTextfield;
    private JTextField startSecTextfield;
    
    private JTextField durationHrsTextfield;
    private JTextField durationMinTextfield;
    private JTextField durationSecTextfield;

    @Override
    public void setController(ControllerMini controllerMini) {
        this.controllerMini = controllerMini;
    }

    @Override
    public void create() {
        frame = new JFrame();
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setLayout(null);
        
        startHrsTextfield = new JTextField();
        startHrsTextfield.setBounds(10, 10, 30, 30);
        frame.add(startHrsTextfield);
        
        startMinTextfield = new JTextField();
        startMinTextfield.setBounds(50, 10, 30, 30);
        frame.add(startMinTextfield);
        
        startSecTextfield = new JTextField();
        startSecTextfield.setBounds(90, 10, 30, 30);
        frame.add(startSecTextfield);
        
        
        durationHrsTextfield = new JTextField();
        durationHrsTextfield.setBounds(FRAME_WIDTH - 135, 10, 30, 30);
        frame.add(durationHrsTextfield);
        
        durationMinTextfield = new JTextField();
        durationMinTextfield.setBounds(FRAME_WIDTH - 95, 10, 30, 30);
        frame.add(durationMinTextfield);
        
        durationSecTextfield = new JTextField();
        durationSecTextfield.setBounds(FRAME_WIDTH - 55, 10, 30, 30);
        frame.add(durationSecTextfield);
        
        
        titleTextfield = new JTextField();
        titleTextfield.setBounds(10, 50, FRAME_WIDTH - 35, 30);
        frame.add(titleTextfield);
        
        JButton okButton = new JButton("Ok");
        okButton.setBounds(10, FRAME_HEIGHT - 80, 100, 30);
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
    
    @Override
    public Chrono getStartTime() {
        return new Chrono(
                Integer.parseInt(startHrsTextfield.getText()),
                Integer.parseInt(startMinTextfield.getText()),
                Integer.parseInt(startSecTextfield.getText()));
    }

    @Override
    public Chrono getDurationTime() {
        return new Chrono(
                Integer.parseInt(durationHrsTextfield.getText()),
                Integer.parseInt(durationMinTextfield.getText()),
                Integer.parseInt(durationSecTextfield.getText()));
    }
}
