package com.company.transitionsFrame;

import com.company.DoubleClickListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.GregorianCalendar;

public class ViewTransitions {
    private ControllerTransitions controllerTransitions;
    private JList<String> list;
    private JTextField dateText;

    private static final int FRAME_WIDTH = 900;
    private static final int FRAME_HEIGHT = 600;
    
    public void setControllerTransitions(ControllerTransitions controllerTransitions) {
        this.controllerTransitions = controllerTransitions;
    }

    public void setDataToList(String[] data) {
        list.setListData(data);
    }

    public void create() {
        JFrame frame = new JFrame();
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setLayout(null);

        ActionListener listenerForEdit = e -> controllerTransitions.handleEditButtonClick();

        list = new JList<>();
        JScrollPane playlist = new JScrollPane(list);
        playlist.setBounds(10, 10, FRAME_WIDTH - 50, FRAME_HEIGHT - 100);
        list.setFont(new Font("Courier new", Font.PLAIN, 14));
        frame.add(playlist);
        list.addMouseListener(new DoubleClickListener(listenerForEdit));

        JButton addButton = new JButton("Add");
        addButton.setBounds(10, FRAME_HEIGHT - 80, 100, 30);
        addButton.addActionListener(e -> controllerTransitions.handleAddButtonClick());
        frame.add(addButton);

        JButton editButton = new JButton("Edit");
        editButton.setBounds(120, FRAME_HEIGHT - 80, 100, 30);
        editButton.addActionListener(listenerForEdit);
        frame.add(editButton);

        JButton removeButton = new JButton("Remove");
        removeButton.setBounds(230, FRAME_HEIGHT - 80, 100, 30);
        removeButton.addActionListener(e -> controllerTransitions.handleRemoveButtonClick());
        frame.add(removeButton);

        JButton generateButton = new JButton("Generate");
        generateButton.setBounds(FRAME_WIDTH - 140, FRAME_HEIGHT - 80, 100, 30);
        generateButton.addActionListener(e -> controllerTransitions.handleGenerateButtonClick());
        frame.add(generateButton);
        
        dateText = new JTextField();
        dateText.setBounds(FRAME_WIDTH - 250, FRAME_HEIGHT - 80, 100, 30);
        frame.add(dateText);

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
    
    public GregorianCalendar getDate() {
        String source = dateText.getText();
        int day = Integer.parseInt(source.substring(0, 2));
        int month = Integer.parseInt(source.substring(3, 5)) - 1;
        int year = Integer.parseInt(source.substring(6, 8)) + 2000;
        return new GregorianCalendar(year, month, day);
    }
}
