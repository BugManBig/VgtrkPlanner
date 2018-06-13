package com.company.federalFrame;

import com.company.DoubleClickListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ViewFederalFrame {
    private String[] listData;

    private ControllerFederalFrame controllerFederalFrame;

    private JList<String> list;

    private static final int FRAME_WIDTH = 900;
    private static final int FRAME_HEIGHT = 600;
    
    public void setController(ControllerFederalFrame controllerSetka) {
        this.controllerFederalFrame = controllerSetka;
    }
    
    public void setDataToList(String[] data) {
        listData = data;
        list.setListData(listData);
    }
    
    public void create() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setLayout(null);

        ActionListener listenerForEdit = e -> controllerFederalFrame.handleEditButtonClick();

        list = new JList<>();
        JScrollPane playlist = new JScrollPane(list);
        playlist.setBounds(10, 10, FRAME_WIDTH - 50, FRAME_HEIGHT - 100);
        list.setFont(new Font("Courier new", Font.PLAIN, 14));
        frame.add(playlist);
        list.addMouseListener(new DoubleClickListener(listenerForEdit));

        JButton addButton = new JButton("Add");
        addButton.setBounds(10, FRAME_HEIGHT - 80, 100, 30);
        addButton.addActionListener(e -> controllerFederalFrame.handleAddButtonClick());
        frame.add(addButton);

        JButton editButton = new JButton("Edit");
        editButton.setBounds(120, FRAME_HEIGHT - 80, 100, 30);
        editButton.addActionListener(listenerForEdit);
        frame.add(editButton);

        JButton removeButton = new JButton("Remove");
        removeButton.setBounds(230, FRAME_HEIGHT - 80, 100, 30);
        removeButton.addActionListener(e -> controllerFederalFrame.handleRemoveButtonClick());
        frame.add(removeButton);

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

}
