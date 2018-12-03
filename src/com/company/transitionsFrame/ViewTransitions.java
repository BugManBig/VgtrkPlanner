package com.company.transitionsFrame;

import com.company.DoubleClickListener;
import com.company.ProjectParams;
import com.company.ProjectSettings;
import com.company.SelectedCellRenderer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ViewTransitions {
    private ControllerTransitions controllerTransitions;
    private JList<String> list;

    private static final int FRAME_WIDTH = Integer.parseInt(ProjectSettings.getParam(ProjectParams.WINDOW_WIDTH));
    private static final int FRAME_HEIGHT = Integer.parseInt(ProjectSettings.getParam(ProjectParams.WINDOW_HEIGHT));

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
        frame.setResizable(false);
        frame.getContentPane().setBackground(Color.decode("#444444"));

        ActionListener listenerForEdit = e -> controllerTransitions.handleEditButtonClick();

        list = new JList<>();
        JScrollPane playlist = new JScrollPane(list);
        playlist.setBounds(10, 10, FRAME_WIDTH - 50, FRAME_HEIGHT - 100);
        int fontSize = Integer.parseInt(ProjectSettings.getParam(ProjectParams.FONT_SIZE));
        list.setFont(new Font("Courier new", Font.PLAIN, fontSize));
        frame.add(playlist);
        list.addMouseListener(new DoubleClickListener(listenerForEdit));
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setBackground(Color.decode("#444444"));
        list.setForeground(Color.decode("#DDDDDD"));
        list.setCellRenderer(new SelectedCellRenderer());

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

        JButton copyButton = new JButton("Дублировать");
        copyButton.setBounds(FRAME_WIDTH - 350, FRAME_HEIGHT - 80, 200, 30);
        copyButton.addActionListener(e -> controllerTransitions.handleCopyButtonClick());
        frame.add(copyButton);

        JButton backButton = new JButton("Меню");
        backButton.setBounds(FRAME_WIDTH - 140, FRAME_HEIGHT - 80, 100, 30);
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
