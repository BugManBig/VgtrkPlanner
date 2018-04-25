package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BroadcastGridImpl implements FrameActions {
    @Override
    public ActionListener getListenerForEditButton(PlaylistDatabase playlistDatabase, FrameWithList frameWithList,
                                                   Application application, JButton buttonForEdit, JList<String> list) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ContextFrame(list.getSelectedIndex()).createFrameForNewElement(playlistDatabase,
                        frameWithList, application);
            }
        };
    }

    @Override
    public ActionListener getListenerForCreateButton(PlaylistDatabase playlistDatabase, FrameWithList frameWithList, Application application) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ContextFrame().createFrameForNewElement(playlistDatabase, frameWithList, application);
            }
        };
    }

    @Override
    public ActionListener getListenerForRemoveButton(PlaylistDatabase playlistDatabase, FrameWithList frameWithList,
                                                     Application application, JList<String> list) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playlistDatabase.remove(list.getSelectedIndex());
                frameWithList.refreshListData();
                frameWithList.checkEnablingButtons();
                application.serialize(playlistDatabase);
            }
        };
    }

    @Override
    public String[] getDataForList(PlaylistDatabase playlistDatabase) {
        String[] playlistLines = new String[playlistDatabase.getSize()];
        for (int i = 0; i < playlistLines.length; i++) {
            playlistLines[i] = playlistDatabase.getPreparedString(i);
        }
        return playlistLines;
    }
}
