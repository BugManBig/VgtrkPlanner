package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class FedTimetable {
    private Application application;

    private static final int FRAME_WIDTH = 900;
    private static final int FRAME_HEIGHT = 600;

    private PlaylistDatabase playlistDatabase;
    private JList<String> list = new JList<>();

    public FedTimetable(Application application) {
        this.application = application;
    }

    public void create() {
        FedTimetable fedTimetable = this;

        JFrame frame = new JFrame("FedTimetable");
        frame.setLocation(500, 200);
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLayout(null);

        list.setFont(new Font("Courier new", Font.PLAIN, 14));

        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setBounds(10, 10, FRAME_WIDTH - 40, 500);
        frame.add(scrollPane);

        JButton buttonForEdit = new JButton("Edit");
        buttonForEdit.setEnabled(false);
        buttonForEdit.setBounds(230, FRAME_HEIGHT - 80, 100, 30);
        buttonForEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ContextFrame(list.getSelectedIndex()).createFrameForNewElement(playlistDatabase, fedTimetable,
                        application, buttonForEdit);
            }
        });
        frame.add(buttonForEdit);

        JButton buttonForCreate = new JButton("Create");
        buttonForCreate.setBounds(10, FRAME_HEIGHT - 80, 100, 30);
        buttonForCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ContextFrame().createFrameForNewElement(playlistDatabase, fedTimetable, application, buttonForEdit);
            }
        });
        frame.add(buttonForCreate);

        JButton buttonForRemove = new JButton("Remove");
        buttonForRemove.setBounds(120, FRAME_HEIGHT - 80, 100, 30);
        buttonForRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playlistDatabase.remove(list.getSelectedIndex());
                refresh();
                buttonForEdit.setEnabled(false);
                application.serialize(playlistDatabase);
            }
        });
        frame.add(buttonForRemove);

        list.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                buttonForEdit.setEnabled(true);
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        frame.repaint();
        frame.revalidate();

        refresh();
    }

    public void refresh() {
        String[] playlistLines = new String[playlistDatabase.getSize()];
        for (int i = 0; i < playlistLines.length; i++) {
            playlistLines[i] = playlistDatabase.getPreparedString(i);
        }
        list.setListData(playlistLines);
    }

    public void setDatabase(PlaylistDatabase playlistDatabase) {
        this.playlistDatabase = playlistDatabase;
    }
}
