package com.company.finalFrame;

import com.company.DaysOfWeek;
import com.company.DoubleClickListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class ViewFinal {
    private ControllerFinal controllerFinal;
    private JList<String> list;
    private JTextField weekDayField;
    private JTextField doubleField;
    private JFrame frame;

    private static final int FRAME_WIDTH = 900;
    private static final int FRAME_HEIGHT = 600;

    public void setControllerFinal(ControllerFinal controllerFinal) {
        this.controllerFinal = controllerFinal;
    }

    public void setWeekDayText(int weekDay) {
        String day = DaysOfWeek.values()[weekDay].toString();
        day = day.substring(0, 1) + day.substring(1).toLowerCase();
        weekDayField.setText(day);
    }
    
    public void setDoubleText(int doubleNumber) {
        if (doubleNumber == 0) {
            doubleField.setText("Федеральное");
        } else {
            doubleField.setText("Дубль-" + doubleNumber);
        }
    }

    public void setDataToList(String[] data) {
        list.setListData(data);
    }

    public void create() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setLayout(null);

        ActionListener listenerForEdit = e -> controllerFinal.handleEditButtonClick();

        list = new JList<>();
        JScrollPane playlist = new JScrollPane(list);
        playlist.setBounds(10, 10, FRAME_WIDTH - 50, FRAME_HEIGHT - 140);
        list.setFont(new Font("Courier new", Font.PLAIN, 14));
        frame.add(playlist);
        list.addMouseListener(new DoubleClickListener(listenerForEdit));

        weekDayField = new JTextField();
        weekDayField.setBounds(FRAME_WIDTH - 410, FRAME_HEIGHT - 120, 100, 30);
        weekDayField.setEditable(false);
        frame.add(weekDayField);
        
        doubleField = new JTextField();
        doubleField.setBounds(FRAME_WIDTH - 410, FRAME_HEIGHT - 80, 100, 30);
        doubleField.setEditable(false);
        frame.add(doubleField);

        JButton addButton = new JButton("Добавить");
        addButton.setBounds(10, FRAME_HEIGHT - 120, 100, 30);
        addButton.addActionListener(e -> controllerFinal.handleAddButtonClick());
        frame.add(addButton);

        JButton editButton = new JButton("Изменить");
        editButton.setBounds(120, FRAME_HEIGHT - 120, 100, 30);
        editButton.addActionListener(listenerForEdit);
        frame.add(editButton);

        JButton removeButton = new JButton("Удалить");
        removeButton.setBounds(230, FRAME_HEIGHT - 120, 100, 30);
        removeButton.addActionListener(e -> controllerFinal.handleRemoveButtonClick());
        frame.add(removeButton);

        JButton prevDayButton = new JButton("<<<");
        prevDayButton.setBounds(FRAME_WIDTH - 520, FRAME_HEIGHT - 120, 100, 30);
        prevDayButton.addActionListener(e -> controllerFinal.handlePrevDayButtonClick());
        frame.add(prevDayButton);

        JButton nextDayButton = new JButton(">>>");
        nextDayButton.setBounds(FRAME_WIDTH - 300, FRAME_HEIGHT - 120, 100, 30);
        nextDayButton.addActionListener(e -> controllerFinal.handleNextDayButtonClick());
        frame.add(nextDayButton);
        
        JButton prevDoubleButton = new JButton("<<<");
        prevDoubleButton.setBounds(FRAME_WIDTH - 520, FRAME_HEIGHT - 80, 100, 30);
        prevDoubleButton.addActionListener(e -> controllerFinal.handlePrevDoubleButtonClick());
        frame.add(prevDoubleButton);
        
        JButton nextDoubleButton = new JButton(">>>");
        nextDoubleButton.setBounds(FRAME_WIDTH - 300, FRAME_HEIGHT - 80, 100, 30);
        nextDoubleButton.addActionListener(e -> controllerFinal.handleNextDoubleButtonClick());
        frame.add(nextDoubleButton);
        
        JButton documentationButton = new JButton("Отчёты");
        documentationButton.setBounds(FRAME_WIDTH - 140, FRAME_HEIGHT - 120, 100, 30);
        documentationButton.addActionListener(e -> controllerFinal.handleDocumentationButtonClick());
        frame.add(documentationButton);

        JButton transitionsButton = new JButton("Генерировать");
        transitionsButton.setBounds(10, FRAME_HEIGHT - 80, 100, 30);
        transitionsButton.addActionListener(e -> controllerFinal.handleGenerateButtonClick());
        frame.add(transitionsButton);

        JButton menuButton = new JButton("Меню");
        menuButton.setBounds(FRAME_WIDTH - 140, FRAME_HEIGHT - 80, 100, 30);
        menuButton.addActionListener(e -> controllerFinal.handleMenuButtonClick());
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

    public void setDateAtFrameTitle(GregorianCalendar date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-YYYY");
        String dateString = simpleDateFormat.format(date.getTime());
        frame.setTitle("Неделя: " + dateString);
    }
}
