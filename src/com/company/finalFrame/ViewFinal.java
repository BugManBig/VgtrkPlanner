package com.company.finalFrame;

import com.company.*;

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

    private static final int FRAME_WIDTH = Integer.parseInt(ProjectSettings.getParam(ProjectParams.WINDOW_WIDTH));
    private static final int FRAME_HEIGHT = Integer.parseInt(ProjectSettings.getParam(ProjectParams.WINDOW_HEIGHT));

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
        String windowBackgroundColor = ProjectSettings.getParam(ProjectParams.WINDOW_BACKGROUND_COLOR);
        String windowFontColor = ProjectSettings.getParam(ProjectParams.WINDOW_FONT_COLOR);

        frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.getContentPane().setBackground(Color.decode(windowBackgroundColor));

        ActionListener listenerForEdit = e -> controllerFinal.handleEditButtonClick();

        list = new JList<>();
        JScrollPane playlist = new JScrollPane(list);
        playlist.setBounds(10, 10, FRAME_WIDTH - 50, FRAME_HEIGHT - 140);
        int fontSize = Integer.parseInt(ProjectSettings.getParam(ProjectParams.FONT_SIZE));
        list.setFont(new Font("Courier new", Font.PLAIN, fontSize));
        frame.add(playlist);
        list.addMouseListener(new DoubleClickListener(listenerForEdit));
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setBackground(Color.decode(windowBackgroundColor));
        list.setForeground(Color.decode(windowFontColor));
        list.setCellRenderer(new SelectedCellRenderer());

        weekDayField = new JTextField();
        weekDayField.setBackground(Color.decode(windowBackgroundColor));
        weekDayField.setForeground(Color.decode(windowFontColor));
        weekDayField.setBounds(FRAME_WIDTH - 410, FRAME_HEIGHT - 120, 100, 30);
        weekDayField.setEditable(false);
        weekDayField.setFocusable(false);
        frame.add(weekDayField);

        doubleField = new JTextField();
        doubleField.setBackground(Color.decode(windowBackgroundColor));
        doubleField.setForeground(Color.decode(windowFontColor));
        doubleField.setBounds(FRAME_WIDTH - 410, FRAME_HEIGHT - 80, 100, 30);
        doubleField.setEditable(false);
        doubleField.setFocusable(false);
        frame.add(doubleField);

        JButton addButton = new JButton("Добавить");
        setButtonDesign(addButton);
        addButton.setBounds(10, FRAME_HEIGHT - 120, 100, 30);
        addButton.addActionListener(e -> controllerFinal.handleAddButtonClick());
        frame.add(addButton);

        JButton editButton = new JButton("Изменить");
        setButtonDesign(editButton);
        editButton.setBounds(120, FRAME_HEIGHT - 120, 100, 30);
        editButton.addActionListener(listenerForEdit);
        frame.add(editButton);

        JButton removeButton = new JButton("Удалить");
        setButtonDesign(removeButton);
        removeButton.setBounds(230, FRAME_HEIGHT - 120, 100, 30);
        removeButton.addActionListener(e -> controllerFinal.handleRemoveButtonClick());
        frame.add(removeButton);

        JButton prevDayButton = new JButton("<<<");
        setButtonDesign(prevDayButton);
        prevDayButton.setBounds(FRAME_WIDTH - 520, FRAME_HEIGHT - 120, 100, 30);
        prevDayButton.addActionListener(e -> controllerFinal.handlePrevDayButtonClick());
        frame.add(prevDayButton);

        JButton nextDayButton = new JButton(">>>");
        setButtonDesign(nextDayButton);
        nextDayButton.setBounds(FRAME_WIDTH - 300, FRAME_HEIGHT - 120, 100, 30);
        nextDayButton.addActionListener(e -> controllerFinal.handleNextDayButtonClick());
        frame.add(nextDayButton);

        JButton prevDoubleButton = new JButton("<<<");
        setButtonDesign(prevDoubleButton);
        prevDoubleButton.setBounds(FRAME_WIDTH - 520, FRAME_HEIGHT - 80, 100, 30);
        prevDoubleButton.addActionListener(e -> controllerFinal.handlePrevDoubleButtonClick());
        frame.add(prevDoubleButton);

        JButton nextDoubleButton = new JButton(">>>");
        setButtonDesign(nextDoubleButton);
        nextDoubleButton.setBounds(FRAME_WIDTH - 300, FRAME_HEIGHT - 80, 100, 30);
        nextDoubleButton.addActionListener(e -> controllerFinal.handleNextDoubleButtonClick());
        frame.add(nextDoubleButton);

        JButton documentationButton = new JButton("Отчёты");
        setButtonDesign(documentationButton);
        documentationButton.setBounds(FRAME_WIDTH - 140, FRAME_HEIGHT - 120, 100, 30);
        documentationButton.addActionListener(e -> controllerFinal.handleDocumentationButtonClick());
        frame.add(documentationButton);

        JButton generateDoublesButton = new JButton("Формирование дублей");
        setButtonDesign(generateDoublesButton);
        generateDoublesButton.setBounds(10, FRAME_HEIGHT - 80, 210, 30);
        generateDoublesButton.addActionListener(e -> controllerFinal.handleGenerateDoublesButtonClick());
        frame.add(generateDoublesButton);

        JButton checkButton = new JButton("Проверка");
        setButtonDesign(checkButton);
        checkButton.setBounds(230, FRAME_HEIGHT - 80, 100, 30);
        checkButton.addActionListener(e -> controllerFinal.handleCheckButtonClick());
        frame.add(checkButton);

        JButton menuButton = new JButton("Меню");
        setButtonDesign(menuButton);
        menuButton.setBounds(FRAME_WIDTH - 140, FRAME_HEIGHT - 80, 100, 30);
        menuButton.addActionListener(e -> controllerFinal.handleMenuButtonClick());
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
        list.ensureIndexIsVisible(index);
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
