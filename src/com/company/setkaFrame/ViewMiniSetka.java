package com.company.setkaFrame;

import com.company.*;

import javax.swing.*;
import java.awt.*;

public class ViewMiniSetka {
    private ControllerMiniSetka controllerMiniSetka;

    private JFrame frame;

    private static final int FRAME_WIDTH = 450;
    private static final int FRAME_HEIGHT = 250;

    private JTextField titleTextfield;

    private JTextField startHrsTextfield;
    private JTextField startMinTextfield;
    private JTextField startSecTextfield;

    private JTextField durationHrsTextfield;
    private JTextField durationMinTextfield;
    private JTextField durationSecTextfield;

    private JCheckBox[] checkBoxes;

    public void setControllerMiniSetka(ControllerMiniSetka controllerMiniSetka) {
        this.controllerMiniSetka = controllerMiniSetka;
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

        JLabel startTimeLabel = new JLabel("Время начала:");
        startTimeLabel.setForeground(Color.decode(windowFontColor));
        startTimeLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        startTimeLabel.setBounds(10, 10, 150, 30);
        frame.add(startTimeLabel);

        startHrsTextfield = new JTextField();
        setTextfieldDesign(startHrsTextfield);
        startHrsTextfield.setBounds(160, 10, 30, 30);
        frame.add(startHrsTextfield);

        startMinTextfield = new JTextField();
        setTextfieldDesign(startMinTextfield);
        startMinTextfield.setBounds(200, 10, 30, 30);
        frame.add(startMinTextfield);

        startSecTextfield = new JTextField();
        setTextfieldDesign(startSecTextfield);
        startSecTextfield.setBounds(240, 10, 30, 30);
        frame.add(startSecTextfield);

        JLabel durationTimeLabel = new JLabel("Продолжительность:");
        durationTimeLabel.setForeground(Color.decode(windowFontColor));
        durationTimeLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        durationTimeLabel.setBounds(10, 50, 150, 30);
        frame.add(durationTimeLabel);

        durationHrsTextfield = new JTextField();
        setTextfieldDesign(durationHrsTextfield);
        durationHrsTextfield.setBounds(160, 50, 30, 30);
        frame.add(durationHrsTextfield);

        durationMinTextfield = new JTextField();
        setTextfieldDesign(durationMinTextfield);
        durationMinTextfield.setBounds(200, 50, 30, 30);
        frame.add(durationMinTextfield);

        durationSecTextfield = new JTextField();
        setTextfieldDesign(durationSecTextfield);
        durationSecTextfield.setBounds(240, 50, 30, 30);
        frame.add(durationSecTextfield);

        JLabel titleLabel = new JLabel("Название:");
        titleLabel.setForeground(Color.decode(windowFontColor));
        titleLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        titleLabel.setBounds(10, 90, 100, 30);
        frame.add(titleLabel);

        titleTextfield = new JTextField();
        setTextfieldDesign(titleTextfield);
        titleTextfield.setFont(new Font("Arial", Font.PLAIN, 16));
        titleTextfield.setHorizontalAlignment(JTextField.LEFT);
        titleTextfield.setBounds(90, 90, FRAME_WIDTH - 115, 30);
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


        JButton okButton = new JButton("Ок");
        setButtonDesign(okButton);
        okButton.setBounds(10, FRAME_HEIGHT - 80, 100, 30);
        okButton.addActionListener(e -> controllerMiniSetka.handleOkButtonClick());
        frame.add(okButton);

        JButton cancelButton = new JButton("Отмена");
        setButtonDesign(cancelButton);
        cancelButton.setBounds(FRAME_WIDTH - 125, FRAME_HEIGHT - 80, 100, 30);
        cancelButton.addActionListener(e -> controllerMiniSetka.handleCancelButtonClick());
        frame.add(cancelButton);

        JButton setAllButton = new JButton("Все дни");
        setButtonDesign(setAllButton);
        setAllButton.setBounds(FRAME_WIDTH / 2 - 50, FRAME_HEIGHT - 80, 100, 30);
        setAllButton.addActionListener(e -> controllerMiniSetka.handleSetAllButtonClick());
        frame.add(setAllButton);

        checkBoxes = new JCheckBox[7];
        for (int i = 0; i < 7; i++) {
            JCheckBox checkBox = new JCheckBox();
            checkBox.setBackground(Color.decode(windowBackgroundColor));
            checkBox.setForeground(Color.decode(windowFontColor));
            checkBox.setFont(new Font("Arial", Font.PLAIN, 15));
            checkBox.setBounds(10 + ((FRAME_WIDTH - 10) / 7) * i, 130, 60, 30);
            checkBox.setText(DaysOfWeek.values()[i].getShortName());
            checkBoxes[i] = checkBox;
            frame.add(checkBox);
        }

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

    public boolean[] getCheckboxesState() {
        boolean[] result = new boolean[7];
        for (int i = 0; i < 7; i++) {
            result[i] = checkBoxes[i].isSelected();
        }
        return result;
    }

    public void setAllCheckboxes() {
        for (JCheckBox checkBox : checkBoxes) {
            checkBox.setSelected(true);
        }
    }

    public boolean isNothingCheckboxesSelected() {
        for (JCheckBox checkBox : checkBoxes) {
            if (checkBox.isSelected()) {
                return false;
            }
        }
        return true;
    }

    public void setFieldsFromPlanElement(PlanElement planElement) {
        startHrsTextfield.setText(planElement.getStartTime().getHoursString());
        startMinTextfield.setText(planElement.getStartTime().getMinutesString());
        startSecTextfield.setText(planElement.getStartTime().getSecondsString());

        durationHrsTextfield.setText(planElement.getDurationTime().getHoursString());
        durationMinTextfield.setText(planElement.getDurationTime().getMinutesString());
        durationSecTextfield.setText(planElement.getDurationTime().getSecondsString());

        titleTextfield.setText(planElement.getTitle());

        boolean[] daysState = planElement.getSelectedDays().getSelectionsArray();
        for (int i = 0; i < 7; i++) {
            checkBoxes[i].setSelected(daysState[i]);
        }

        titleTextfield.requestFocus();
    }
}
