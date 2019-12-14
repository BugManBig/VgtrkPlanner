package com.company.finalFrame;

import com.company.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class ViewFinal {
    private ControllerFinal controllerFinal;
    private JList<String> list;
    private JLabel weekDayLabel;
    private JLabel doubleLabel;
    private JLabel dateLabel;
    private JFrame frame;

    private static final int FRAME_WIDTH = Integer.parseInt(ProjectSettings.getParam(ProjectParams.WINDOW_WIDTH));
    private static final int FRAME_HEIGHT = Integer.parseInt(ProjectSettings.getParam(ProjectParams.WINDOW_HEIGHT));

    public void setControllerFinal(ControllerFinal controllerFinal) {
        this.controllerFinal = controllerFinal;
    }

    public void setWeekDayText(int weekDay) {
        String day = DaysOfWeek.values()[weekDay].toString();
        day = day.substring(0, 1) + day.substring(1).toLowerCase();
        weekDayLabel.setText(day);
    }

    public void setDoubleText(int doubleNumber) {
        if (doubleNumber == 0) {
            doubleLabel.setText("Федеральное");
        } else {
            doubleLabel.setText("Дубль-" + doubleNumber);
        }
    }

    public void setDataToList(String[] data) {
        list.setListData(data);
    }

    public void create(GregorianCalendar date) {
        String windowBackgroundColor = ProjectSettings.getParam(ProjectParams.WINDOW_BACKGROUND_COLOR);
        String windowFontColor = ProjectSettings.getParam(ProjectParams.WINDOW_FONT_COLOR);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        String dateString = "Неделя: " + simpleDateFormat.format(date.getTime());
        date.add(Calendar.DAY_OF_MONTH, 6);
        dateString += " - " + simpleDateFormat.format(date.getTime());
        frame = new JFrame(dateString);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.getContentPane().setBackground(Color.decode(windowBackgroundColor));

        ActionListener listenerForEdit = e -> controllerFinal.handleEditButtonClick();

        list = new JList<>();
        JScrollPane playlist = new JScrollPane(list);
        playlist.setBounds(10, 30, FRAME_WIDTH - 50, FRAME_HEIGHT - 160);
        int fontSize = Integer.parseInt(ProjectSettings.getParam(ProjectParams.FONT_SIZE));
        list.setFont(new Font("Courier new", Font.PLAIN, fontSize));
        frame.add(playlist);
        list.addMouseListener(new DoubleClickListener(listenerForEdit));
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setBackground(Color.decode(windowBackgroundColor));
        list.setForeground(Color.decode(windowFontColor));
        list.setCellRenderer(new SelectedCellRenderer());

        JLabel listLabels = new JLabel("   Начало      Продолж.    Название");
        listLabels.setFont(new Font("Courier new", Font.BOLD, fontSize));
        listLabels.setForeground(Color.decode(windowFontColor));
        listLabels.setBounds(12, 0, 2000, 30);
        frame.add(listLabels);

        weekDayLabel = new JLabel();
        weekDayLabel.setForeground(Color.decode(windowFontColor));
        weekDayLabel.setBounds(FRAME_WIDTH - 410, FRAME_HEIGHT - 120, 120, 30);
        weekDayLabel.setFont(new Font("Arial", Font.BOLD, 15));
        weekDayLabel.setHorizontalAlignment(SwingConstants.CENTER);
        frame.add(weekDayLabel);

        doubleLabel = new JLabel();
        doubleLabel.setForeground(Color.decode(windowFontColor));
        doubleLabel.setBounds(FRAME_WIDTH - 410, FRAME_HEIGHT - 80, 120, 30);
        doubleLabel.setFont(new Font("Arial", Font.BOLD, 15));
        doubleLabel.setHorizontalAlignment(JTextField.CENTER);
        frame.add(doubleLabel);

        dateLabel = new JLabel();
        dateLabel.setForeground(Color.decode(windowFontColor));
        dateLabel.setBounds(FRAME_WIDTH - 750, FRAME_HEIGHT - 120, 200, 70);
        dateLabel.setFont(new Font("Arial", Font.BOLD, 30));
        dateLabel.setHorizontalAlignment(JTextField.CENTER);
        frame.add(dateLabel);

        JButton addButton = new JButton("Добавить");
        setButtonDesign(addButton);
        addButton.setBounds(10, FRAME_HEIGHT - 120, 120, 30);
        addButton.addActionListener(e -> controllerFinal.handleAddButtonClick());
        frame.add(addButton);

        JButton editButton = new JButton("Изменить");
        setButtonDesign(editButton);
        editButton.setBounds(140, FRAME_HEIGHT - 120, 120, 30);
        editButton.addActionListener(listenerForEdit);
        frame.add(editButton);

        JButton removeButton = new JButton("Удалить");
        setButtonDesign(removeButton);
        removeButton.setBounds(270, FRAME_HEIGHT - 120, 120, 30);
        removeButton.addActionListener(e -> controllerFinal.handleRemoveButtonClick());
        frame.add(removeButton);

        JButton prevDayButton = new JButton("<<<");
        setButtonDesign(prevDayButton);
        prevDayButton.setBounds(FRAME_WIDTH - 480, FRAME_HEIGHT - 120, 60, 30);
        prevDayButton.addActionListener(e -> controllerFinal.handlePrevDayButtonClick());
        frame.add(prevDayButton);

        JButton nextDayButton = new JButton(">>>");
        setButtonDesign(nextDayButton);
        nextDayButton.setBounds(FRAME_WIDTH - 280, FRAME_HEIGHT - 120, 60, 30);
        nextDayButton.addActionListener(e -> controllerFinal.handleNextDayButtonClick());
        frame.add(nextDayButton);

        JButton prevDoubleButton = new JButton("<<<");
        setButtonDesign(prevDoubleButton);
        prevDoubleButton.setBounds(FRAME_WIDTH - 480, FRAME_HEIGHT - 80, 60, 30);
        prevDoubleButton.addActionListener(e -> controllerFinal.handlePrevDoubleButtonClick());
        frame.add(prevDoubleButton);

        JButton nextDoubleButton = new JButton(">>>");
        setButtonDesign(nextDoubleButton);
        nextDoubleButton.setBounds(FRAME_WIDTH - 280, FRAME_HEIGHT - 80, 60, 30);
        nextDoubleButton.addActionListener(e -> controllerFinal.handleNextDoubleButtonClick());
        frame.add(nextDoubleButton);

        JButton documentationButton = new JButton("Отчёты");
        setButtonDesign(documentationButton);
        documentationButton.setBounds(FRAME_WIDTH - 140, FRAME_HEIGHT - 120, 100, 30);
        documentationButton.addActionListener(e -> controllerFinal.handleDocumentationButtonClick());
        frame.add(documentationButton);

        JButton generateDoublesButton = new JButton("Формирование дублей");
        setButtonDesign(generateDoublesButton);
        generateDoublesButton.setBounds(10, FRAME_HEIGHT - 80, 250, 30);
        generateDoublesButton.addActionListener(e -> controllerFinal.handleGenerateDoublesButtonClick());
        frame.add(generateDoublesButton);

        JButton duplicateButton = new JButton("Копировать");
        setButtonDesign(duplicateButton);
        duplicateButton.setBounds(270, FRAME_HEIGHT - 80, 120, 30);
        duplicateButton.addActionListener(e -> controllerFinal.handleDuplicateButtonClick());
        frame.add(duplicateButton);

        JButton menuButton = new JButton("Меню");
        setButtonDesign(menuButton);
        menuButton.setBounds(FRAME_WIDTH - 140, FRAME_HEIGHT - 80, 100, 30);
        menuButton.addActionListener(e -> controllerFinal.handleMenuButtonClick());
        frame.add(menuButton);

        JButton serialButton = new JButton("Сериал");
        setButtonDesign(serialButton);
        serialButton.setBounds(400, FRAME_HEIGHT - 80, 100, 30);
        serialButton.addActionListener(e -> controllerFinal.handleSerialButtonClick());
        frame.add(serialButton);

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

    public void setCurrentDateAtFrame(GregorianCalendar date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        String dateString = simpleDateFormat.format(date.getTime());
        dateLabel.setText(dateString);
    }
}
