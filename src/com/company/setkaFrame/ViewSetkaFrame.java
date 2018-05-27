package com.company.setkaFrame;

import javax.swing.*;

public class ViewSetkaFrame implements ViewSetka {
    private String[] listData;

    private ControllerSetka controllerSetka;

    private JList<String> list;

    private static final int FRAME_WIDTH = 900;
    private static final int FRAME_HEIGHT = 600;

    @Override
    public void setController(ControllerSetka controllerSetka) {
        this.controllerSetka = controllerSetka;
    }

    @Override
    public void setDataToList(String[] data) {
        listData = data;
        list.setListData(listData);
    }

    @Override
    public void create() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setVisible(true);
        frame.setLayout(null);

        list = new JList<>();
        JScrollPane playlist = new JScrollPane(list);
        playlist.setBounds(10, 10, FRAME_WIDTH - 50, FRAME_HEIGHT - 100);
        frame.add(playlist);

        frame.repaint();
        frame.revalidate();
        frame.setLocationRelativeTo(null);
    }
}
