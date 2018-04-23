package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.TimeUnit;

public class FedTimetable {
    private static final int FRAME_WIDTH = 600;
    private static final int FRAME_HEIGHT = 600;

    public void create() {
        JFrame frame = new JFrame("FedTimetable");
        frame.setLocation(300, 300);
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLayout(null);

        JList<String> list = new JList<>();
        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setBounds(10, 10, FRAME_WIDTH - 40, 100);
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
        frame.setLocation(200, 200);
        frame.setVisible(true);
        frame.setLayout(null);

        JTextField hoursText = new JTextField();
        hoursText.setBounds(10, 10, 25, 25);
        frame.add(hoursText);

        JTextField minutesText = new JTextField();
        minutesText.setBounds(40, 10, 25, 25);
        frame.add(minutesText);

        hoursText.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (hoursText.getText().length() == 2) {
                    minutesText.requestFocus();
                }
            }
        });

        frame.repaint();
        frame.revalidate();
    }
}
