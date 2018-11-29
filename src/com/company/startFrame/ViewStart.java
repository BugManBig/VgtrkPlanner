package com.company.startFrame;

import javax.swing.*;

public class ViewStart {
    private ControllerStart controllerStart;

    private static final int FRAME_WIDTH = 300;
    private static final int FRAME_HEIGHT = 210;

    private JFrame frame;

    public void setControllerStart(ControllerStart controllerStart) {
        this.controllerStart = controllerStart;
    }

    public void create() {
        frame = new JFrame("Выберите нужный вариант");
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        JButton createButton = new JButton("Сетка");
        createButton.setBounds(FRAME_WIDTH / 2 - 100, 10, 200, 30);
        createButton.addActionListener(e -> controllerStart.handleCreateButtonClick());
        frame.add(createButton);

        JButton loadButton = new JButton("Загрузить неделю");
        loadButton.setBounds(FRAME_WIDTH / 2 - 100, 50, 200, 30);
        loadButton.addActionListener(e -> controllerStart.handleLoadButtonClick());
        frame.add(loadButton);

        JButton generateButton = new JButton("Формирование РР");
        generateButton.setBounds(FRAME_WIDTH / 2 - 100, 90, 200, 30);
        generateButton.addActionListener(e -> controllerStart.handleGenerateButtonClick());
        frame.add(generateButton);

        JButton transitionsButton = new JButton("Справочник замен");
        transitionsButton.setBounds(FRAME_WIDTH / 2 - 100, 130, 200, 30);
        transitionsButton.addActionListener(e -> controllerStart.handleTransitionsButtonClick());
        frame.add(transitionsButton);

        frame.repaint();
        frame.revalidate();
    }

    public void close() {
        frame.dispose();
    }
}
