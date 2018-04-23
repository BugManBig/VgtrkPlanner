package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class ContextFrame {
    private int lineId = -1;

    public ContextFrame() {

    }

    public ContextFrame(int lineId) {
        this.lineId = lineId;
    }

    public void createFrameForNewElement(PlaylistDatabase playlistDatabase,
                                         FedTimetable fedTimetable, Application application, JButton button) {
        JFrame frame = new JFrame("Create element");
        frame.setSize(400, 200);
        frame.setLocation(600, 400);
        frame.setVisible(true);
        frame.setLayout(null);


        JLabel timeLabel = new JLabel("Time:");
        timeLabel.setBounds(10, 10, 50, 25);
        frame.add(timeLabel);

        JTextField hoursText = new JTextField();
        hoursText.setBounds(60, 10, 25, 25);
        frame.add(hoursText);

        JTextField minutesText = new JTextField();
        minutesText.setBounds(90, 10, 25, 25);
        frame.add(minutesText);

        JTextField secondsText = new JTextField();
        secondsText.setBounds(120, 10, 25, 25);
        frame.add(secondsText);


        JLabel chronoLabel = new JLabel("Chrono:");
        chronoLabel.setBounds(230, 10, 50, 25);
        frame.add(chronoLabel);

        JTextField chronoHoursText = new JTextField();
        chronoHoursText.setBounds(290, 10, 25, 25);
        frame.add(chronoHoursText);

        JTextField chronoMinutesText = new JTextField();
        chronoMinutesText.setBounds(320, 10, 25, 25);
        frame.add(chronoMinutesText);

        JTextField chronoSecondsText = new JTextField();
        chronoSecondsText.setBounds(350, 10, 25, 25);
        frame.add(chronoSecondsText);


        JLabel titleLabel = new JLabel("Title:");
        titleLabel.setBounds(10, 50, 50, 25);
        frame.add(titleLabel);

        JTextField titleTextfield = new JTextField();
        titleTextfield.setBounds(60, 50, 315, 25);
        frame.add(titleTextfield);

        hoursText.addKeyListener(new KeyImpl(hoursText, minutesText));
        minutesText.addKeyListener(new KeyImpl(minutesText, secondsText));
        secondsText.addKeyListener(new KeyImpl(secondsText, chronoHoursText));
        chronoHoursText.addKeyListener(new KeyImpl(chronoHoursText, chronoMinutesText));
        chronoMinutesText.addKeyListener(new KeyImpl(chronoMinutesText, chronoSecondsText));
        chronoSecondsText.addKeyListener(new KeyImpl(chronoSecondsText, titleTextfield));

        JCheckBox[] checkBoxes = addCheckboxes(frame);

        if (lineId != -1) {
            SoundElement soundElement = playlistDatabase.get(lineId);
            writeValueInField(hoursText, TimeNames.MAIN_TIME, TimeNames.HOURS, soundElement);
            writeValueInField(minutesText, TimeNames.MAIN_TIME, TimeNames.MINUTES, soundElement);
            writeValueInField(secondsText, TimeNames.MAIN_TIME, TimeNames.SECONDS, soundElement);
            writeValueInField(chronoHoursText, TimeNames.CHRONO_TIME, TimeNames.HOURS, soundElement);
            writeValueInField(chronoMinutesText, TimeNames.CHRONO_TIME, TimeNames.MINUTES, soundElement);
            writeValueInField(chronoSecondsText, TimeNames.CHRONO_TIME, TimeNames.SECONDS, soundElement);
            titleTextfield.setText(soundElement.getTitle());
            boolean[] checkBoxesValues = soundElement.getWeekDays();
            for (int i = 0; i < 7; i++) {
                if (checkBoxesValues[i]) {
                    checkBoxes[i].setSelected(true);
                }
            }
        }

        hoursText.selectAll();

        JButton buttonOk = new JButton("Create");
        if (lineId != -1) {
            buttonOk.setText("Save");
        }
        buttonOk.setBounds(10, 120, 100, 30);
        buttonOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int hours = getNumberFromTextfield(hoursText);
                int minutes = getNumberFromTextfield(minutesText);
                int seconds = getNumberFromTextfield(secondsText);
                int chronoHours = getNumberFromTextfield(chronoHoursText);
                int chronoMinutes = getNumberFromTextfield(chronoMinutesText);
                int chronoSeconds = getNumberFromTextfield(chronoSecondsText);
                String title = titleTextfield.getText();
                boolean[] weekDays = new boolean[7];
                for (int i = 0; i < 7; i++) {
                    if (checkBoxes[i].isSelected()) {
                        weekDays[i] = true;
                    }
                }
                int mainTime = hours * 60 * 60 + minutes * 60 + seconds;
                int chronoTime = chronoHours * 60 * 60 + chronoMinutes * 60 + chronoSeconds;
                SoundElement soundElement = new SoundElement(mainTime, chronoTime, title, weekDays);
                if (lineId == -1) {
                    playlistDatabase.add(soundElement);
                } else {
                    playlistDatabase.set(lineId, soundElement);
                }
                frame.dispose();
                fedTimetable.refresh();
                fedTimetable.checkEnablingButtons();
                application.serialize(playlistDatabase);
            }
        });
        frame.add(buttonOk);

        JButton buttonCancel = new JButton("Cancel");
        buttonCancel.setBounds(130, 120, 100, 30);
        buttonCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
        frame.add(buttonCancel);

        frame.repaint();
        frame.revalidate();
    }

    private int getNumberFromTextfield(JTextField textField) {
        if (Objects.equals(textField.getText(), "")) {
            return 0;
        }
        return Integer.parseInt(textField.getText());
    }

    private JCheckBox[] addCheckboxes(JFrame frame) {
        JCheckBox[] checkBoxes = new JCheckBox[7];
        String[] days = {"Mon", "Tue", "Wen", "Thu", "Fri", "Sat", "Sun"};
        for (int i = 0; i < 7; i++) {
            JCheckBox checkBox = new JCheckBox();
            checkBox.setText(days[i]);
            checkBox.setBounds(10 + i * 50, 80, 52, 25);
            frame.add(checkBox);
            checkBoxes[i] = checkBox;
        }
        return checkBoxes;
    }

    private void writeValueInField(JTextField textField, TimeNames timeType, TimeNames timeUnit, SoundElement soundElement) {
        textField.setText(String.valueOf(soundElement.get(timeType, timeUnit)));
    }
}
