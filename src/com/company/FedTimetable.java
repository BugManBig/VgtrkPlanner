package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FedTimetable {
    private static final int FRAME_WIDTH = 600;
    private static final int FRAME_HEIGHT = 600;

    public void create() {
        JFrame frame = new JFrame("FedTimetable");
        frame.setLocation(500, 200);
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLayout(null);

        JList<String> list = new JList<>();
        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setBounds(10, 10, FRAME_WIDTH - 40, 500);
        frame.add(scrollPane);

        JButton buttonForCreate = new JButton("Create");
        buttonForCreate.setBounds(10, FRAME_HEIGHT - 80, 100, 30);
        buttonForCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createFrameForNewElement();
            }
        });
        frame.add(buttonForCreate);

        frame.repaint();
        frame.revalidate();
    }

    private void createFrameForNewElement() {
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


        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(10, 50, 50, 25);
        frame.add(nameLabel);

        JTextField nameTextfield = new JTextField();
        nameTextfield.setBounds(60, 50, 250, 25);
        frame.add(nameTextfield);

        hoursText.addKeyListener(new KeyImpl(hoursText, minutesText));
        minutesText.addKeyListener(new KeyImpl(minutesText, secondsText));
        secondsText.addKeyListener(new KeyImpl(secondsText, chronoHoursText));
        chronoHoursText.addKeyListener(new KeyImpl(chronoHoursText, chronoMinutesText));
        chronoMinutesText.addKeyListener(new KeyImpl(chronoMinutesText, chronoSecondsText));
        chronoSecondsText.addKeyListener(new KeyImpl(chronoSecondsText, nameTextfield));

        addCheckboxes(frame);

        JButton buttonOk = new JButton("Create");
        buttonOk.setBounds(10, 120, 100, 30);
        buttonOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

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

    private void addCheckboxes(JFrame frame) {
        String[] days = {"Mon", "Tue", "Wen", "Thu", "Fri", "Sat", "Sun"};
        for (int i = 0; i < 7; i++) {
            JCheckBox checkBox = new JCheckBox();
            checkBox.setText(days[i]);
            checkBox.setBounds(10 + i * 50, 80, 52, 25);
            frame.add(checkBox);
        }
    }
}
