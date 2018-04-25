package com.company;

import javax.swing.*;
import java.awt.event.ActionListener;

public class FederalTimetableImpl implements FrameActions {
    @Override
    public ActionListener getListenerForEditButton(PlaylistDatabase playlistDatabase, FrameWithList frameWithList, Application application, JButton buttonForEdit, JList<String> list) {
        return null;
    }

    @Override
    public ActionListener getListenerForCreateButton(PlaylistDatabase playlistDatabase, FrameWithList frameWithList, Application application) {
        return null;
    }

    @Override
    public ActionListener getListenerForRemoveButton(PlaylistDatabase playlistDatabase, FrameWithList frameWithList, Application application, JList<String> list) {
        return null;
    }

    @Override
    public String[] getDataForList(PlaylistDatabase playlistDatabase) {
        SoundElement soundElement;
        String[] data = new String[playlistDatabase.getSize()];
        for (int i = 0; i < data.length; i++) {
            soundElement = playlistDatabase.get(i);
            data[i] = AdditionalUtilities.getStandardTime(soundElement, true) + "    "
                    + AdditionalUtilities.getStandardTime(soundElement, false) + "    "
                    + soundElement.getTitle();
        }
        return data;
    }
}
