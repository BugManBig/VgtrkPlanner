package com.company.transitionsFrame;

import com.company.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewMiniTransitions {
    private ControllerMiniTransitions controllerMiniTransitions;

    private JFrame frame;

    private static final int FRAME_WIDTH = 700;
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
    private JLabel radioLabel;
    private JRadioButton[] weekdaysRadiobuttons;

    public void setControllerMiniTransitions(ControllerMiniTransitions controllerMiniTransitions) {
        this.controllerMiniTransitions = controllerMiniTransitions;
    }
    
    public void create() {
        String windowBackgroundColor = ProjectSettings.getParam(ProjectParams.WINDOW_BACKGROUND_COLOR);
        String windowFontColor = ProjectSettings.getParam(ProjectParams.WINDOW_FONT_COLOR);

        frame = new JFrame("Редактирование");
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.getContentPane().setBackground(Color.decode(windowBackgroundColor));

        JLabel startTimeLabel = new JLabel("Время начала РР:");
        startTimeLabel.setForeground(Color.decode(windowFontColor));
        startTimeLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        startTimeLabel.setBounds(10, 10, 150, 30);
        frame.add(startTimeLabel);

        startHrsTextfield = new JTextField();
        setTextfieldDesign(startHrsTextfield);
        startHrsTextfield.setBounds(170, 10, 30, 30);
        frame.add(startHrsTextfield);

        startMinTextfield = new JTextField();
        setTextfieldDesign(startMinTextfield);
        startMinTextfield.setBounds(210, 10, 30, 30);
        frame.add(startMinTextfield);

        startSecTextfield = new JTextField();
        setTextfieldDesign(startSecTextfield);
        startSecTextfield.setBounds(250, 10, 30, 30);
        frame.add(startSecTextfield);

        JLabel endTimeLabel = new JLabel("Время окончания РР:");
        endTimeLabel.setForeground(Color.decode(windowFontColor));
        endTimeLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        endTimeLabel.setBounds(10, 50, 150, 30);
        frame.add(endTimeLabel);

        endHrsTextfield = new JTextField();
        setTextfieldDesign(endHrsTextfield);
        endHrsTextfield.setBounds(170, 50, 30, 30);
        frame.add(endHrsTextfield);

        endMinTextfield = new JTextField();
        setTextfieldDesign(endMinTextfield);
        endMinTextfield.setBounds(210, 50, 30, 30);
        frame.add(endMinTextfield);

        endSecTextfield = new JTextField();
        setTextfieldDesign(endSecTextfield);
        endSecTextfield.setBounds(250, 50, 30, 30);
        frame.add(endSecTextfield);

        JLabel doubleTimeLabel = new JLabel("Время дубля:");
        doubleTimeLabel.setForeground(Color.decode(windowFontColor));
        doubleTimeLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        doubleTimeLabel.setBounds(10, 90, 150, 30);
        frame.add(doubleTimeLabel);

        transitionHrsTextfield = new JTextField();
        setTextfieldDesign(transitionHrsTextfield);
        transitionHrsTextfield.setBounds(170, 90, 30, 30);
        frame.add(transitionHrsTextfield);
        
        transitionMinTextfield = new JTextField();
        setTextfieldDesign(transitionMinTextfield);
        transitionMinTextfield.setBounds(210, 90, 30, 30);
        frame.add(transitionMinTextfield);
        
        transitionSecTextfield = new JTextField();
        setTextfieldDesign(transitionSecTextfield);
        transitionSecTextfield.setBounds(250, 90, 30, 30);
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
        setButtonDesign(okButton);
        okButton.setBounds(10, FRAME_HEIGHT - 80, 100, 30);
        okButton.addActionListener(e -> controllerMiniTransitions.handleOkButtonClick());
        frame.add(okButton);

        JButton cancelButton = new JButton("Отмена");
        setButtonDesign(cancelButton);
        cancelButton.setBounds(FRAME_WIDTH - 125, FRAME_HEIGHT - 80, 100, 30);
        cancelButton.addActionListener(e -> controllerMiniTransitions.handleCancelButtonClick());
        frame.add(cancelButton);

        JButton setAllButton = new JButton("Все дни");
        setButtonDesign(setAllButton);
        setAllButton.setBounds(FRAME_WIDTH / 2 - 50, FRAME_HEIGHT - 80, 100, 30);
        setAllButton.addActionListener(e -> controllerMiniTransitions.handleSetAllButtonClick());
        frame.add(setAllButton);

        weekdaysCheckboxes = new JCheckBox[7];
        for (int i = 0; i < 7; i++) {
            JCheckBox checkBox = new JCheckBox();
            checkBox.setBackground(Color.decode(windowBackgroundColor));
            checkBox.setForeground(Color.decode(windowFontColor));
            checkBox.setBounds(10 + 60 * i, 130, 60, 30);
            checkBox.setText(DaysOfWeek.values()[i].getShortName());
            checkBox.setFont(new Font("Arial", Font.PLAIN, 15));
            weekdaysCheckboxes[i] = checkBox;
            frame.add(checkBox);
        }

        int doublesCount = Integer.parseInt(ProjectSettings.getParam(ProjectParams.DOUBLES_COUNT));
        doublesCheckboxes = new JCheckBox[doublesCount];
        for (int i = 0; i < doublesCount; i++) {
            JCheckBox checkBox = new JCheckBox();
            checkBox.setBackground(Color.decode(windowBackgroundColor));
            checkBox.setForeground(Color.decode(windowFontColor));
            checkBox.setBounds(10 + 60 * i, 160, 60, 30);
            checkBox.setText("Д" + (i + 1));
            doublesCheckboxes[i] = checkBox;
            frame.add(checkBox);
        }

        radioLabel = new JLabel("Перенос на:");
        radioLabel.setForeground(Color.decode(windowFontColor));
        radioLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        radioLabel.setBounds(10, 250, 150, 30);
        frame.add(radioLabel);

        radiobuttonsCheckbox = new JCheckBox("Перенос дня недели для дублей");
        radiobuttonsCheckbox.setBackground(Color.decode(windowBackgroundColor));
        radiobuttonsCheckbox.setForeground(Color.decode(windowFontColor));
        radiobuttonsCheckbox.setBounds(10, 220, 400, 30);
        radiobuttonsCheckbox.setFont(new Font("Arial", Font.PLAIN, 15));
        radiobuttonsCheckbox.addActionListener(e -> {
            setRadiobuttonsVisible(radiobuttonsCheckbox.isSelected());
        });
        frame.add(radiobuttonsCheckbox);

        weekdaysRadiobuttons = new JRadioButton[7];
        for (int i = 0; i < 7; i++) {
            JRadioButton radioButton = new JRadioButton();
            radioButton.setBackground(Color.decode(windowBackgroundColor));
            radioButton.setForeground(Color.decode(windowFontColor));
            radioButton.setBounds(100 + 60 * i, 250, 50, 30);
            radioButton.setText(DaysOfWeek.values()[i].getShortName());
            radioButton.setFont(new Font("Arial", Font.PLAIN, 15));
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
        setRadiobuttonsVisible(false);

        frame.repaint();
        frame.revalidate();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void setTextfieldDesign(JTextField textField) {
        textField.setBackground(Color.decode(ProjectSettings.getParam(ProjectParams.WINDOW_BACKGROUND_COLOR)));
        textField.setForeground(Color.decode(ProjectSettings.getParam(ProjectParams.WINDOW_FONT_COLOR)));
        textField.setCaretColor(Color.decode(ProjectSettings.getParam(ProjectParams.WINDOW_FONT_COLOR)));
        textField.setFont(new Font("Arial", Font.BOLD, 17));
        textField.setHorizontalAlignment(JTextField.CENTER);
    }

    private void setButtonDesign(JButton button) {
        button.setBackground(Color.decode(ProjectSettings.getParam(ProjectParams.BUTTON_BACKGROUND_COLOR)));
        button.setForeground(Color.decode(ProjectSettings.getParam(ProjectParams.BUTTON_FONT_COLOR)));
    }

    public boolean isRadiobuttonsCheckboxSelected() {
        return radiobuttonsCheckbox.isSelected();
    }

    private void setRadiobuttonsVisible(boolean b) {
        radioLabel.setVisible(b);
        for (int i = 0; i < 7; i++) {
            weekdaysRadiobuttons[i].setVisible(b);
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
        boolean[] result = new boolean[11];
        int doublesCount = Integer.parseInt(ProjectSettings.getParam(ProjectParams.DOUBLES_COUNT));
        for (int i = 0; i < doublesCount; i++) {
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
        startHrsTextfield.setText(transitionElement.getStartTime().getHoursString());
        startMinTextfield.setText(transitionElement.getStartTime().getMinutesString());
        startSecTextfield.setText(transitionElement.getStartTime().getSecondsString());
        
        endHrsTextfield.setText(transitionElement.getEndTime().getHoursString());
        endMinTextfield.setText(transitionElement.getEndTime().getMinutesString());
        endSecTextfield.setText(transitionElement.getEndTime().getSecondsString());
        
        transitionHrsTextfield.setText(transitionElement.getTransitionTime().getHoursString());
        transitionMinTextfield.setText(transitionElement.getTransitionTime().getMinutesString());
        transitionSecTextfield.setText(transitionElement.getTransitionTime().getSecondsString());
        
        boolean[] weekdaysSelections = transitionElement.getSelectedDays().getSelectionsArray();
        for (int i = 0; i < 7; i++) {
            weekdaysCheckboxes[i].setSelected(weekdaysSelections[i]);
        }
        
        boolean[] doublesSelections = transitionElement.getSelectedDoubles().getSelectionsArray();
        for (int i = 0; i < 11; i++) {
            doublesCheckboxes[i].setSelected(doublesSelections[i]);
        }

        int offsetWeekday = transitionElement.getOffsetToWeekday();
        if (offsetWeekday > -1) {
            radiobuttonsCheckbox.setSelected(true);
            setRadiobuttonsVisible(true);
            selectRadiobutton(offsetWeekday);
        }
    }
}
