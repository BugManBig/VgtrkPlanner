package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class FrameWithList {
    private static final int FRAME_WIDTH = 900;
    private static final int FRAME_HEIGHT = 600;

    private PlaylistDatabase playlistDatabase;
    private Application application;

    private JList<String> list = new JList<>();

    private JButton buttonForEdit;
    private JButton buttonForRemove;

    private FrameActions frameActions;

    public FrameWithList(FrameActions frameActions, PlaylistDatabase playlistDatabase, Application application) {
        this.frameActions = frameActions;
        this.playlistDatabase = playlistDatabase;
        this.application = application;
    }

    public void create() {
        FrameWithList frameWithList = this;

        JFrame frame = new JFrame("Broadcast grid");
        frame.setLocation(500, 200);
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLayout(null);

        list.setFont(new Font("Courier new", Font.PLAIN, 14));

        ActionListener listenerForEditButton = frameActions.getListenerForEditButton(playlistDatabase, frameWithList,
                application, buttonForEdit, list);

        list.addMouseListener(new DoubleClickListener(listenerForEditButton));

        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setBounds(10, 10, FRAME_WIDTH - 40, 500);
        frame.add(scrollPane);

        buttonForEdit = new JButton("Edit");
        buttonForEdit.setBounds(230, FRAME_HEIGHT - 80, 100, 30);
        buttonForEdit.addActionListener(listenerForEditButton);
        frame.add(buttonForEdit);

        JButton buttonForCreate = new JButton("Create");
        buttonForCreate.setBounds(10, FRAME_HEIGHT - 80, 100, 30);
        buttonForCreate.addActionListener(frameActions.getListenerForCreateButton(playlistDatabase,
                frameWithList, application));
        frame.add(buttonForCreate);

        buttonForRemove = new JButton("Remove");
        buttonForRemove.setBounds(120, FRAME_HEIGHT - 80, 100, 30);
        buttonForRemove.addActionListener(frameActions.getListenerForRemoveButton(playlistDatabase, frameWithList,
                application, list));
        frame.add(buttonForRemove);

        list.addMouseListener(new ClickListener(this));

        checkEnablingButtons();

        refreshListData();

        frame.repaint();
        frame.revalidate();
    }

    public void refreshListData() {
        list.setListData(frameActions.getDataForList(playlistDatabase));
    }

    public void checkEnablingButtons() {
        boolean isSelected = list.getSelectedIndex() != -1;
        buttonForEdit.setEnabled(isSelected);
        buttonForRemove.setEnabled(isSelected);
    }

    public void selectLine(int id) {
        for (int i = 0; i < playlistDatabase.getSize(); i++) {
            if (playlistDatabase.get(i).getId() == id) {
                list.setSelectedIndex(i);
                return;
            }
        }
    }
}
