package com.company.setkaFrame;

import com.company.DoubleClickListener;
import com.company.ProjectParams;
import com.company.ProjectSettings;
import com.company.SelectedCellRenderer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ViewSetka {
    private ControllerSetka controllerSetka;
    private JList<String> list;
    private JFrame frame;

    private static final int FRAME_WIDTH = Integer.parseInt(ProjectSettings.getParam(ProjectParams.WINDOW_WIDTH));
    private static final int FRAME_HEIGHT = Integer.parseInt(ProjectSettings.getParam(ProjectParams.WINDOW_HEIGHT));

    public void setControllerSetka(ControllerSetka controllerSetka) {
        this.controllerSetka = controllerSetka;
    }

    public void setDataToList(String[] data) {
        list.setListData(data);
    }

    public void create() {
        String windowBackgroundColor = ProjectSettings.getParam(ProjectParams.WINDOW_BACKGROUND_COLOR);
        String windowFontColor = ProjectSettings.getParam(ProjectParams.WINDOW_FONT_COLOR);

        frame = new JFrame("Сетка");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.getContentPane().setBackground(Color.decode(windowBackgroundColor));

        ActionListener listenerForEdit = e -> controllerSetka.handleEditButtonClick();
        
        list = new JList<>();
        JScrollPane playlist = new JScrollPane(list);
        playlist.setBounds(10, 10, FRAME_WIDTH - 50, FRAME_HEIGHT - 100);
        int fontSize = Integer.parseInt(ProjectSettings.getParam(ProjectParams.FONT_SIZE));
        list.setFont(new Font("Courier new", Font.PLAIN, fontSize));
        frame.add(playlist);
        list.addMouseListener(new DoubleClickListener(listenerForEdit));
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setBackground(Color.decode(windowBackgroundColor));
        list.setForeground(Color.decode(windowFontColor));
        list.setCellRenderer(new SelectedCellRenderer());

        JButton addButton = new JButton("Добавить");
        setButtonDesign(addButton);
        addButton.setBounds(10, FRAME_HEIGHT - 80, 100, 30);
        addButton.addActionListener(e -> controllerSetka.handleAddButtonClick());
        frame.add(addButton);
        
        JButton editButton = new JButton("Изменить");
        setButtonDesign(editButton);
        editButton.setBounds(120, FRAME_HEIGHT - 80, 100, 30);
        editButton.addActionListener(listenerForEdit);
        frame.add(editButton);

        JButton removeButton = new JButton("Удалить");
        setButtonDesign(removeButton);
        removeButton.setBounds(230, FRAME_HEIGHT - 80, 100, 30);
        removeButton.addActionListener(e -> controllerSetka.handleRemoveButtonClick());
        frame.add(removeButton);

        JButton duplicateButton = new JButton("Дублировать");
        setButtonDesign(duplicateButton);
        duplicateButton.setBounds(FRAME_WIDTH - 350, FRAME_HEIGHT - 80, 200, 30);
        duplicateButton.addActionListener(e -> controllerSetka.handleDuplicateButtonClick());
        frame.add(duplicateButton);

        JButton menuButton = new JButton("Меню");
        setButtonDesign(menuButton);
        menuButton.setBounds(FRAME_WIDTH - 140, FRAME_HEIGHT - 80, 100, 30);
        menuButton.addActionListener(e -> controllerSetka.handleMenuButtonClick());
        frame.add(menuButton);
        
        frame.repaint();
        frame.revalidate();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void setButtonDesign(JButton button) {
        button.setBackground(Color.decode(ProjectSettings.getParam(ProjectParams.BUTTON_BACKGROUND_COLOR)));
        button.setForeground(Color.decode(ProjectSettings.getParam(ProjectParams.BUTTON_FONT_COLOR)));
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
