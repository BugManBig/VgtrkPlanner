package com.company.transitionsFrame;

import com.company.DoubleClickListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ViewTransitions {
    private ControllerTransitions controllerTransitions;
    private JList<String> list;

    private static final int FRAME_WIDTH = 900;
    private static final int FRAME_HEIGHT = 600;

    private JFrame frame;
    
    public void setControllerTransitions(ControllerTransitions controllerTransitions) {
        this.controllerTransitions = controllerTransitions;
    }

    public void setDataToList(String[] data) {
        list.setListData(data);
    }

    public void create() {
        frame = new JFrame("Справочник замен");
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(null);

        ActionListener listenerForEdit = e -> controllerTransitions.handleEditButtonClick();

        list = new JList<>();
        JScrollPane playlist = new JScrollPane(list);
        playlist.setBounds(10, 10, FRAME_WIDTH - 50, FRAME_HEIGHT - 100);
        list.setFont(new Font("Courier new", Font.PLAIN, 14));
        frame.add(playlist);
        list.addMouseListener(new DoubleClickListener(listenerForEdit));

        JButton addButton = new JButton("Добавить");
        addButton.setBounds(10, FRAME_HEIGHT - 80, 100, 30);
        addButton.addActionListener(e -> controllerTransitions.handleAddButtonClick());
        frame.add(addButton);

        JButton editButton = new JButton("Изменить");
        editButton.setBounds(120, FRAME_HEIGHT - 80, 100, 30);
        editButton.addActionListener(listenerForEdit);
        frame.add(editButton);

        JButton removeButton = new JButton("Удалить");
        removeButton.setBounds(230, FRAME_HEIGHT - 80, 100, 30);
        removeButton.addActionListener(e -> controllerTransitions.handleRemoveButtonClick());
        frame.add(removeButton);

        JButton generateButton = new JButton("Генерировать");
        generateButton.setBounds(FRAME_WIDTH - 140, FRAME_HEIGHT - 80, 100, 30);
        generateButton.addActionListener(e -> controllerTransitions.handleGenerateButtonClick());
        frame.add(generateButton);

        JButton backButton = new JButton("Назад");
        backButton.setBounds(FRAME_WIDTH - 250, FRAME_HEIGHT - 80, 100, 30);
        backButton.addActionListener(e -> controllerTransitions.handleBackButtonClick());
        frame.add(backButton);

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
