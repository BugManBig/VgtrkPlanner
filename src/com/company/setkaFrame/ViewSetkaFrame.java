package com.company.setkaFrame;

import com.company.DoubleClickListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ViewSetkaFrame implements ViewSetka {
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
        list.setListData(data);
    }

    @Override
    public void create() {
        JFrame frame = new JFrame();
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

        JButton addButton = new JButton("Add");
        addButton.setBounds(10, FRAME_HEIGHT - 80, 100, 30);
        addButton.addActionListener(e -> controllerSetka.handleAddButtonClick());
        frame.add(addButton);
        
        JButton editButton = new JButton("Edit");
        editButton.setBounds(120, FRAME_HEIGHT - 80, 100, 30);
        editButton.addActionListener(listenerForEdit);
        frame.add(editButton);

        JButton removeButton = new JButton("Remove");
        removeButton.setBounds(230, FRAME_HEIGHT - 80, 100, 30);
        removeButton.addActionListener(e -> controllerSetka.handleRemoveButtonClick());
        frame.add(removeButton);
        
        JButton generateButton = new JButton("Generate");
        generateButton.setBounds(FRAME_WIDTH - 140, FRAME_HEIGHT - 80, 100, 30);
        generateButton.addActionListener(e -> controllerSetka.handleGenerateButtonClick());
        frame.add(generateButton);
        
        frame.repaint();
        frame.revalidate();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    @Override
    public int getSelectedLine() {
        return list.getSelectedIndex();
    }

    @Override
    public void selectLine(int index) {
        list.setSelectedIndex(index);
    }
}
