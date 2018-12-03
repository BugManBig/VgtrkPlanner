package com.company.transitionsFrame;

import com.company.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewMiniTransitions {
    private ControllerMiniTransitions controllerMiniTransitions;

    private JFrame frame;

    private static final int FRAME_WIDTH = 450;
    private static final int FRAME_HEIGHT = 370;

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

    private JCheckBox radiobuttonsCheckbox;
    private JRadioButton[] weekdaysRadiobuttons;

    public void setControllerMiniTransitions(ControllerMiniTransitions controllerMiniTransitions) {
        this.controllerMiniTransitions = controllerMiniTransitions;
    }
    
    public void create() {
        frame = new JFrame("Редактирование");
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setLayout(null);
        frame.setResizable(false);

        JLabel startTimeLabel = new JLabel("Время начала (от):");
        startTimeLabel.setBounds(10, 10, 150, 30);
        frame.add(startTimeLabel);

        startHrsTextfield = new JTextField();
        startHrsTextfield.setBounds(150, 10, 30, 30);
        frame.add(startHrsTextfield);

        startMinTextfield = new JTextField();
        startMinTextfield.setBounds(190, 10, 30, 30);
        frame.add(startMinTextfield);

        startSecTextfield = new JTextField();
        startSecTextfield.setBounds(230, 10, 30, 30);
        frame.add(startSecTextfield);

        JLabel endTimeLabel = new JLabel("Время окончания (до):");
        endTimeLabel.setBounds(10, 50, 150, 30);
        frame.add(endTimeLabel);

        endHrsTextfield = new JTextField();
        endHrsTextfield.setBounds(150, 50, 30, 30);
        frame.add(endHrsTextfield);

        endMinTextfield = new JTextField();
        endMinTextfield.setBounds(190, 50, 30, 30);
        frame.add(endMinTextfield);

        endSecTextfield = new JTextField();
        endSecTextfield.setBounds(230, 50, 30, 30);
        frame.add(endSecTextfield);

        JLabel doubleTimeLabel = new JLabel("Время дубля:");
        doubleTimeLabel.setBounds(10, 90, 150, 30);
        frame.add(doubleTimeLabel);

        transitionHrsTextfield = new JTextField();
        transitionHrsTextfield.setBounds(150, 90, 30, 30);
        frame.add(transitionHrsTextfield);
        
        transitionMinTextfield = new JTextField();
        transitionMinTextfield.setBounds(190, 90, 30, 30);
        frame.add(transitionMinTextfield);
        
        transitionSecTextfield = new JTextField();
        transitionSecTextfield.setBounds(230, 90, 30, 30);
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


        JButton okButton = new JButton("Ок");
        okButton.setBounds(10, FRAME_HEIGHT - 80, 100, 30);
        okButton.addActionListener(e -> controllerMiniTransitions.handleOkButtonClick());
        frame.add(okButton);

        JButton cancelButton = new JButton("Отмена");
        cancelButton.setBounds(FRAME_WIDTH - 125, FRAME_HEIGHT - 80, 100, 30);
        cancelButton.addActionListener(e -> controllerMiniTransitions.handleCancelButtonClick());
        frame.add(cancelButton);

        JButton setAllButton = new JButton("Все дни");
        setAllButton.setBounds(FRAME_WIDTH / 2 - 50, FRAME_HEIGHT - 80, 100, 30);
        setAllButton.addActionListener(e -> controllerMiniTransitions.handleSetAllButtonClick());
        frame.add(setAllButton);

        weekdaysCheckboxes = new JCheckBox[7];
        for (int i = 0; i < 7; i++) {
            JCheckBox checkBox = new JCheckBox();
            checkBox.setBounds(10 + ((FRAME_WIDTH - 10) / 7) * i, 130, 60, 30);
            checkBox.setText(DaysOfWeek.values()[i].getShortName());
            weekdaysCheckboxes[i] = checkBox;
            frame.add(checkBox);
        }

        doublesCheckboxes = new JCheckBox[4];
        for (int i = 0; i < 4; i++) {
            JCheckBox checkBox = new JCheckBox();
            checkBox.setBounds(10 + ((FRAME_WIDTH - 10) / 7) * i, 160, 60, 30);
            checkBox.setText("Д" + (i + 1));
            doublesCheckboxes[i] = checkBox;
            frame.add(checkBox);
        }

        radiobuttonsCheckbox = new JCheckBox("Перенос дня недели для дублей. Перенос на:");
        radiobuttonsCheckbox.setBounds(10, 220, 300, 30);
        radiobuttonsCheckbox.addActionListener(e -> {
            setRadiobuttonsEnabled(radiobuttonsCheckbox.isSelected());
        });
        frame.add(radiobuttonsCheckbox);

        weekdaysRadiobuttons = new JRadioButton[7];
        for (int i = 0; i < 7; i++) {
            JRadioButton radioButton = new JRadioButton();
            radioButton.setBounds(10 + ((FRAME_WIDTH - 10) / 7) * i, 250, 60, 30);
            radioButton.setText(DaysOfWeek.values()[i].getShortName());
            int k = i;
            radioButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    for (int j = 0; j < 7; j++) {
                        if (j != k) {
                            weekdaysRadiobuttons[j].setSelected(false);
                        } else {
                            weekdaysRadiobuttons[j].setSelected(true);
                        }
                    }
                }
            });
            weekdaysRadiobuttons[i] = radioButton;
            frame.add(radioButton);
        }
        setRadiobuttonsEnabled(false);

        frame.repaint();
        frame.revalidate();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public boolean isRadiobuttonsCheckboxSelected() {
        return radiobuttonsCheckbox.isSelected();
    }

    private void setRadiobuttonsEnabled(boolean b) {
        for (int i = 0; i < 7; i++) {
            weekdaysRadiobuttons[i].setEnabled(b);
            weekdaysRadiobuttons[i].setSelected(false);
        }
        if (b) {
            selectRadiobutton(0);
        }
    }

    public int getSelectedRadiobutton() {
        int index = -1;
        if (!radiobuttonsCheckbox.isSelected()) {
            return index;
        }
        for (int i = 0; i < 7; i++) {
            if (weekdaysRadiobuttons[i].isSelected()) {
                index = i;
            }
        }
        return index;
    }

    private void selectRadiobutton(int index) {
        for (int i = 0; i < 7; i++) {
            weekdaysRadiobuttons[i].setSelected(false);
        }
        weekdaysRadiobuttons[index].setSelected(true);
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

    public void setAllDaysCheckboxes() {
        for (JCheckBox checkBox : weekdaysCheckboxes) {
            checkBox.setSelected(true);
        }
    }

    public boolean isNothingWeekCheckboxesSelected() {
        for (JCheckBox checkBox : weekdaysCheckboxes) {
            if (checkBox.isSelected()) {
                return false;
            }
        }
        return true;
    }

    public boolean isNothingDoublesCheckboxesSelected() {
        for (JCheckBox checkBox : doublesCheckboxes) {
            if (checkBox.isSelected()) {
                return false;
            }
        }
        return true;
    }

    public void setFieldsFromTransitionElement(TransitionElement transitionElement) {
        startHrsTextfield.setText(String.valueOf(transitionElement.getStartTime().getHours()));
        startMinTextfield.setText(String.valueOf(transitionElement.getStartTime().getMinutes()));
        startSecTextfield.setText(String.valueOf(transitionElement.getStartTime().getSeconds()));
        
        endHrsTextfield.setText(String.valueOf(transitionElement.getEndTime().getHours()));
        endMinTextfield.setText(String.valueOf(transitionElement.getEndTime().getMinutes()));
        endSecTextfield.setText(String.valueOf(transitionElement.getEndTime().getSeconds()));
        
        transitionHrsTextfield.setText(String.valueOf(transitionElement.getTransitionTime().getHours()));
        transitionMinTextfield.setText(String.valueOf(transitionElement.getTransitionTime().getMinutes()));
        transitionSecTextfield.setText(String.valueOf(transitionElement.getTransitionTime().getSeconds()));
        
        boolean[] weekdaysSelections = transitionElement.getSelectedDays().getSelectionsArray();
        for (int i = 0; i < 7; i++) {
            weekdaysCheckboxes[i].setSelected(weekdaysSelections[i]);
        }
        
        boolean[] doublesSelections = transitionElement.getSelectedDoubles().getSelectionsArray();
        for (int i = 0; i < 4; i++) {
            doublesCheckboxes[i].setSelected(doublesSelections[i]);
        }

        int offsetWeekday = transitionElement.getOffsetToWeekday();
        if (offsetWeekday > -1) {
            radiobuttonsCheckbox.setSelected(true);
            setRadiobuttonsEnabled(true);
            selectRadiobutton(offsetWeekday);
        }
    }
}
