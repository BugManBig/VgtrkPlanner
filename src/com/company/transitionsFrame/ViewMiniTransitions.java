package com.company.transitionsFrame;

import com.company.Chrono;
import com.company.DaysOfWeek;
import com.company.ListenersAdding;
import com.company.PlanElement;

import javax.swing.*;

public class ViewMiniTransitions {
    private ControllerMiniTransitions controllerMiniTransitions;

    private JFrame frame;

    private static final int FRAME_WIDTH = 450;
    private static final int FRAME_HEIGHT = 400;

    private JTextField startHrsTextfield;
    private JTextField startMinTextfield;
    private JTextField startSecTextfield;
    
    private JTextField endHrsTextfield;
    private JTextField endMinTextfield;
    private JTextField endSecTextfield;
    
    private JTextField transitionHrsTextfield;
    private JTextField transitionMinTextfield;
    private JTextField transitionSecTextfield;
    
    private JCheckBox[] weekdaysCheckboxes;
    private JCheckBox[] doublesCheckboxes;

    public void setControllerMiniTransitions(ControllerMiniTransitions controllerMiniTransitions) {
        this.controllerMiniTransitions = controllerMiniTransitions;
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

        endHrsTextfield = new JTextField();
        endHrsTextfield.setBounds(10, 50, 30, 30);
        frame.add(endHrsTextfield);

        endMinTextfield = new JTextField();
        endMinTextfield.setBounds(50, 50, 30, 30);
        frame.add(endMinTextfield);

        endSecTextfield = new JTextField();
        endSecTextfield.setBounds(90, 50, 30, 30);
        frame.add(endSecTextfield);
        
        transitionHrsTextfield = new JTextField();
        transitionHrsTextfield.setBounds(10, 90, 30, 30);
        frame.add(transitionHrsTextfield);
        
        transitionMinTextfield = new JTextField();
        transitionMinTextfield.setBounds(50, 90, 30, 30);
        frame.add(transitionMinTextfield);
        
        transitionSecTextfield = new JTextField();
        transitionSecTextfield.setBounds(90, 90, 30, 30);
        frame.add(transitionSecTextfield);


        ListenersAdding.addKeyListener(startHrsTextfield, startMinTextfield);
        ListenersAdding.addKeyListener(startMinTextfield, startSecTextfield);
        ListenersAdding.addKeyListener(startSecTextfield, endHrsTextfield);
        ListenersAdding.addKeyListener(endHrsTextfield, endMinTextfield);
        ListenersAdding.addKeyListener(endMinTextfield, endSecTextfield);
        ListenersAdding.addKeyListener(endSecTextfield, transitionHrsTextfield);
        ListenersAdding.addKeyListener(transitionHrsTextfield, transitionMinTextfield);
        ListenersAdding.addKeyListener(transitionMinTextfield, transitionSecTextfield);

        ListenersAdding.addMouseListener(startHrsTextfield);
        ListenersAdding.addMouseListener(startMinTextfield);
        ListenersAdding.addMouseListener(startSecTextfield);
        ListenersAdding.addMouseListener(endHrsTextfield);
        ListenersAdding.addMouseListener(endMinTextfield);
        ListenersAdding.addMouseListener(endSecTextfield);
        ListenersAdding.addMouseListener(transitionHrsTextfield);
        ListenersAdding.addMouseListener(transitionMinTextfield);
        ListenersAdding.addMouseListener(transitionSecTextfield);


        JButton okButton = new JButton("Ok");
        okButton.setBounds(10, FRAME_HEIGHT - 80, 100, 30);
        okButton.addActionListener(e -> controllerMiniTransitions.handleOkButtonClick());
        frame.add(okButton);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setBounds(FRAME_WIDTH - 125, FRAME_HEIGHT - 80, 100, 30);
        cancelButton.addActionListener(e -> controllerMiniTransitions.handleCancelButtonClick());
        frame.add(cancelButton);

        JButton setAllButton = new JButton("Set all");
        setAllButton.setBounds(FRAME_WIDTH / 2 - 50, FRAME_HEIGHT - 80, 100, 30);
        setAllButton.addActionListener(e -> controllerMiniTransitions.handleSetAllButtonClick());
        frame.add(setAllButton);

        weekdaysCheckboxes = new JCheckBox[7];
        for (int i = 0; i < 7; i++) {
            JCheckBox checkBox = new JCheckBox();
            checkBox.setBounds(10 + ((FRAME_WIDTH - 10) / 7) * i, 130, 60, 30);
            checkBox.setText(DaysOfWeek.values()[i].toString());
            weekdaysCheckboxes[i] = checkBox;
            frame.add(checkBox);
        }
        
        doublesCheckboxes = new JCheckBox[4];
        for (int i = 0; i < 4; i++) {
            JCheckBox checkBox = new JCheckBox();
            checkBox.setBounds(10 + ((FRAME_WIDTH - 10) / 7) * i, 160, 60, 30);
            checkBox.setText("D" + (i + 1));
            doublesCheckboxes[i] = checkBox;
            frame.add(checkBox);
        }

        frame.repaint();
        frame.revalidate();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void close() {
        frame.dispose();
    }

    public Chrono getStartTime() {
        return new Chrono(
                Integer.parseInt(startHrsTextfield.getText()),
                Integer.parseInt(startMinTextfield.getText()),
                Integer.parseInt(startSecTextfield.getText()));
    }

    public Chrono getEndTime() {
        return new Chrono(
                Integer.parseInt(endHrsTextfield.getText()),
                Integer.parseInt(endMinTextfield.getText()),
                Integer.parseInt(endSecTextfield.getText()));
    }
    
    public Chrono getTransitionTime() {
        return new Chrono(
                Integer.parseInt(transitionHrsTextfield.getText()),
                Integer.parseInt(transitionMinTextfield.getText()),
                Integer.parseInt(transitionSecTextfield.getText()));
    }

    public boolean[] getWeekdaysCheckboxes() {
        boolean[] result = new boolean[7];
        for (int i = 0; i < 7; i++) {
            result[i] = weekdaysCheckboxes[i].isSelected();
        }
        return result;
    }
    
    public boolean[] getDoublesCheckboxes() {
        boolean[] result = new boolean[4];
        for (int i = 0; i < 4; i++) {
            result[i] = doublesCheckboxes[i].isSelected();
        }
        return result;
    }

    public void setAllCheckboxes() {
        for (JCheckBox checkBox : weekdaysCheckboxes) {
            checkBox.setSelected(true);
        }
    }

    public void setFieldsFromPlanElement(PlanElement planElement) {
        startHrsTextfield.setText(String.valueOf(planElement.getStartTime().getHours()));
        startMinTextfield.setText(String.valueOf(planElement.getStartTime().getMinutes()));
        startSecTextfield.setText(String.valueOf(planElement.getStartTime().getSeconds()));

        endHrsTextfield.setText(String.valueOf(planElement.getDurationTime().getHours()));
        endMinTextfield.setText(String.valueOf(planElement.getDurationTime().getMinutes()));
        endSecTextfield.setText(String.valueOf(planElement.getDurationTime().getSeconds()));

        boolean[] daysState = planElement.getSelectedDays().getSelectedDays();
        for (int i = 0; i < 7; i++) {
            weekdaysCheckboxes[i].setSelected(daysState[i]);
        }
    }

}
