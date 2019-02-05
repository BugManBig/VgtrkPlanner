package com.company.startFrame;

import com.company.ProjectParams;
import com.company.ProjectSettings;

import javax.swing.*;
import java.awt.*;

public class ViewStart {
    private ControllerStart controllerStart;

    private static final int FRAME_WIDTH = 300;
    private static final int FRAME_HEIGHT = 250;

    private JFrame frame;

    public void setControllerStart(ControllerStart controllerStart) {
        this.controllerStart = controllerStart;
    }

    public void create() {
        String windowBackgroundColor = ProjectSettings.getParam(ProjectParams.WINDOW_BACKGROUND_COLOR);

        frame = new JFrame("Меню");
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.getContentPane().setBackground(Color.decode(windowBackgroundColor));

        JButton setkaButton = new JButton("Сетка");
        setButtonDesign(setkaButton);
        setkaButton.setBounds(FRAME_WIDTH / 2 - 100, 30, 200, 30);
        setkaButton.addActionListener(e -> controllerStart.handleSetkaButtonClick());
        frame.add(setkaButton);

        JButton loadButton = new JButton("Загрузить неделю");
        setButtonDesign(loadButton);
        loadButton.setBounds(FRAME_WIDTH / 2 - 100, 70, 200, 30);
        loadButton.addActionListener(e -> controllerStart.handleLoadButtonClick());
        frame.add(loadButton);

        JButton generateButton = new JButton("Формирование РР");
        setButtonDesign(generateButton);
        generateButton.setBounds(FRAME_WIDTH / 2 - 100, 110, 200, 30);
        generateButton.addActionListener(e -> controllerStart.handleGenerateButtonClick());
        frame.add(generateButton);

        JButton transitionsButton = new JButton("Справочник замен");
        setButtonDesign(transitionsButton);
        transitionsButton.setBounds(FRAME_WIDTH / 2 - 100, 150, 200, 30);
        transitionsButton.addActionListener(e -> controllerStart.handleTransitionsButtonClick());
        frame.add(transitionsButton);

        frame.repaint();
        frame.revalidate();
    }

    private void setButtonDesign(JButton button) {
        button.setBackground(Color.decode(ProjectSettings.getParam(ProjectParams.BUTTON_BACKGROUND_COLOR)));
        button.setForeground(Color.decode(ProjectSettings.getParam(ProjectParams.BUTTON_FONT_COLOR)));
    }

    public void close() {
        frame.dispose();
    }
}
