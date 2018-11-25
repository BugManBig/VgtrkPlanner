package com.company.setkaFrame;

import com.company.DoubleClickListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ViewSetka {
    private ControllerSetka controllerSetka;
    private JList<String> list;
    private JFrame frame;

    private static final int FRAME_WIDTH = 900;
    private static final int FRAME_HEIGHT = 600;

    public void setControllerSetka(ControllerSetka controllerSetka) {
        this.controllerSetka = controllerSetka;
    }

    public void setDataToList(String[] data) {
        list.setListData(data);
    }

    public void create() {
        frame = new JFrame("Сетка");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setLayout(null);

        ActionListener listenerForEdit = e -> controllerSetka.handleEditButtonClick();
        
        list = new JList<>();
        JScrollPane playlist = new JScrollPane(list);
        playlist.setBounds(10, 10, FRAME_WIDTH - 50, FRAME_HEIGHT - 100);
        list.setFont(new Font("Courier new", Font.PLAIN, 14));
        frame.add(playlist);
        list.addMouseListener(new DoubleClickListener(listenerForEdit));

        JButton addButton = new JButton("Добавить");
        addButton.setBounds(10, FRAME_HEIGHT - 80, 100, 30);
        addButton.addActionListener(e -> controllerSetka.handleAddButtonClick());
        frame.add(addButton);
        
        JButton editButton = new JButton("Изменить");
        editButton.setBounds(120, FRAME_HEIGHT - 80, 100, 30);
        editButton.addActionListener(listenerForEdit);
        frame.add(editButton);

        JButton removeButton = new JButton("Удалить");
        removeButton.setBounds(230, FRAME_HEIGHT - 80, 100, 30);
        removeButton.addActionListener(e -> controllerSetka.handleRemoveButtonClick());
        frame.add(removeButton);

        JButton menuButton = new JButton("Меню");
        menuButton.setBounds(FRAME_WIDTH - 140, FRAME_HEIGHT - 80, 100, 30);
        menuButton.addActionListener(e -> controllerSetka.handleMenuButtonClick());
        frame.add(menuButton);
        
        frame.repaint();
        frame.revalidate();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public int getSelectedLine() {
        return list.getSelectedIndex();
    }

    public void selectLine(int index) {
        list.setSelectedIndex(index);
    }

    public void close() {
        frame.dispose();
    }
}
