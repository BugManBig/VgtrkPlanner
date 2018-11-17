package com.company.setkaFrame;

import com.company.Chrono;
import com.company.DaysOfWeek;
import com.company.ListenersAdding;
import com.company.PlanElement;

import javax.swing.*;

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
        frame = new JFrame("Редактирование");
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setLayout(null);

        JLabel startTimeLabel = new JLabel("Время начала:");
        startTimeLabel.setBounds(10, 10, 100, 30);
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

        JLabel durationTimeLabel = new JLabel("Продолжительность:");
        durationTimeLabel.setBounds(10, 50, 150, 30);
        frame.add(durationTimeLabel);

        durationHrsTextfield = new JTextField();
        durationHrsTextfield.setBounds(150, 50, 30, 30);
        frame.add(durationHrsTextfield);

        durationMinTextfield = new JTextField();
        durationMinTextfield.setBounds(190, 50, 30, 30);
        frame.add(durationMinTextfield);

        durationSecTextfield = new JTextField();
        durationSecTextfield.setBounds(230, 50, 30, 30);
        frame.add(durationSecTextfield);

        JLabel titleLabel = new JLabel("Название:");
        titleLabel.setBounds(10, 90, 100, 30);
        frame.add(titleLabel);

        titleTextfield = new JTextField();
        titleTextfield.setBounds(80, 90, FRAME_WIDTH - 105, 30);
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
        okButton.setBounds(10, FRAME_HEIGHT - 80, 100, 30);
        okButton.addActionListener(e -> controllerMiniSetka.handleOkButtonClick());
        frame.add(okButton);

        JButton cancelButton = new JButton("Отмена");
        cancelButton.setBounds(FRAME_WIDTH - 125, FRAME_HEIGHT - 80, 100, 30);
        cancelButton.addActionListener(e -> controllerMiniSetka.handleCancelButtonClick());
        frame.add(cancelButton);

        JButton setAllButton = new JButton("Все дни");
        setAllButton.setBounds(FRAME_WIDTH / 2 - 50, FRAME_HEIGHT - 80, 100, 30);
        setAllButton.addActionListener(e -> controllerMiniSetka.handleSetAllButtonClick());
        frame.add(setAllButton);

        checkBoxes = new JCheckBox[7];
        for (int i = 0; i < 7; i++) {
            JCheckBox checkBox = new JCheckBox();
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
        startHrsTextfield.setText(String.valueOf(planElement.getStartTime().getHours()));
        startMinTextfield.setText(String.valueOf(planElement.getStartTime().getMinutes()));
        startSecTextfield.setText(String.valueOf(planElement.getStartTime().getSeconds()));

        durationHrsTextfield.setText(String.valueOf(planElement.getDurationTime().getHours()));
        durationMinTextfield.setText(String.valueOf(planElement.getDurationTime().getMinutes()));
        durationSecTextfield.setText(String.valueOf(planElement.getDurationTime().getSeconds()));

        titleTextfield.setText(planElement.getTitle());

        boolean[] daysState = planElement.getSelectedDays().getSelectionsArray();
        for (int i = 0; i < 7; i++) {
            checkBoxes[i].setSelected(daysState[i]);
        }
    }
}
