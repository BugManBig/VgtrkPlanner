package com.company;

import javax.swing.*;
import java.awt.event.ActionListener;

public interface FrameActions {
    ActionListener getListenerForEditButton(PlaylistDatabase playlistDatabase, FrameWithList frameWithList,
                                            Application application, JButton buttonForEdit, JList<String> list);
    ActionListener getListenerForCreateButton(PlaylistDatabase playlistDatabase, FrameWithList frameWithList,
                                              Application application);
    ActionListener getListenerForRemoveButton(PlaylistDatabase playlistDatabase, FrameWithList frameWithList,
                                              Application application, JList<String> list);
    String[] getDataForList(PlaylistDatabase playlistDatabase);
}
