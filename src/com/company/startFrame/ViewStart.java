package com.company.startFrame;

import javax.swing.*;

public class ViewStart {
    private ControllerStart controllerStart;

    private static final int FRAME_WIDTH = 300;
    private static final int FRAME_HEIGHT = 200;

    JFrame frame;

    public void setControllerStart(ControllerStart controllerStart) {
        this.controllerStart = controllerStart;
    }

    public void create() {
        frame = new JFrame();
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);



        JButton createButton = new JButton("Create");
        createButton.setBounds(FRAME_WIDTH / 2 - 50, 10, 100, 30);
        createButton.addActionListener(e -> controllerStart.handleCreateButtonClick());
        frame.add(createButton);

        frame.repaint();
        frame.revalidate();
    }

    public void close() {
        frame.dispose();
    }
}
