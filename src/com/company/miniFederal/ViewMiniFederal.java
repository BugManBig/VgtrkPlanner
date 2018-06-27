package com.company.miniFederal;

import com.company.Chrono;
import com.company.ListenersAdding;
import com.company.PlanElement;

import javax.swing.*;

public class ViewMiniFederal {
    private ControllerMiniFederal controllerMiniFederal;

    private JFrame frame;

    private static final int FRAME_WIDTH = 450;
    private static final int FRAME_HEIGHT = 170;

    private JTextField titleTextfield;

    private JTextField startHrsTextfield;
    private JTextField startMinTextfield;
    private JTextField startSecTextfield;

    private JTextField durationHrsTextfield;
    private JTextField durationMinTextfield;
    private JTextField durationSecTextfield;

    public void setControllerMiniFederal(ControllerMiniFederal controllerMiniFederal) {
        this.controllerMiniFederal = controllerMiniFederal;
    }

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


        ListenersAdding.addKeyListener(startHrsTextfield, startMinTextfield);
        ListenersAdding.addKeyListener(startMinTextfield, startSecTextfield);
        ListenersAdding.addKeyListener(startSecTextfield, durationHrsTextfield);
        ListenersAdding.addKeyListener(durationHrsTextfield, durationMinTextfield);
        ListenersAdding.addKeyListener(durationMinTextfield, durationSecTextfield);
        ListenersAdding.addKeyListener(durationSecTextfield, titleTextfield);

        ListenersAdding.addMouseListener(startHrsTextfield);
        ListenersAdding.addMouseListener(startMinTextfield);
        ListenersAdding.addMouseListener(startSecTextfield);
        ListenersAdding.addMouseListener(durationHrsTextfield);
        ListenersAdding.addMouseListener(durationMinTextfield);
        ListenersAdding.addMouseListener(durationSecTextfield);
        

        JButton okButton = new JButton("Ok");
        okButton.setBounds(10, FRAME_HEIGHT - 80, 100, 30);
        okButton.addActionListener(e -> controllerMiniFederal.handleOkButtonClick());
        frame.add(okButton);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setBounds(FRAME_WIDTH - 125, FRAME_HEIGHT - 80, 100, 30);
        cancelButton.addActionListener(e -> controllerMiniFederal.handleCancelButtonClick());
        frame.add(cancelButton);

        frame.repaint();
        frame.revalidate();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void close() {
        frame.dispose();
    }

    public String getTitleText() {
        return titleTextfield.getText();
    }

    public Chrono getStartTime() {
        return new Chrono(
                Integer.parseInt(startHrsTextfield.getText()),
                Integer.parseInt(startMinTextfield.getText()),
                Integer.parseInt(startSecTextfield.getText()));
    }

    public Chrono getDurationTime() {
        return new Chrono(
                Integer.parseInt(durationHrsTextfield.getText()),
                Integer.parseInt(durationMinTextfield.getText()),
                Integer.parseInt(durationSecTextfield.getText()));
    }

    public void setFieldsFromPlanElement(PlanElement planElement) {
        startHrsTextfield.setText(String.valueOf(planElement.getStartTime().getHours()));
        startMinTextfield.setText(String.valueOf(planElement.getStartTime().getMinutes()));
        startSecTextfield.setText(String.valueOf(planElement.getStartTime().getSeconds()));

        durationHrsTextfield.setText(String.valueOf(planElement.getDurationTime().getHours()));
        durationMinTextfield.setText(String.valueOf(planElement.getDurationTime().getMinutes()));
        durationSecTextfield.setText(String.valueOf(planElement.getDurationTime().getSeconds()));

        titleTextfield.setText(planElement.getTitle());
    }
}
