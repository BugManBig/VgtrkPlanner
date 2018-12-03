package com.company;

import javax.swing.*;
import java.awt.*;

public class SelectedCellRenderer extends DefaultListCellRenderer {
    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        Component component = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        if (isSelected) {
            component.setBackground(Color.decode(ProjectSettings.getParam(ProjectParams.SELECTED_LINE_BACKGROUND_COLOR)));
            component.setForeground(Color.decode(ProjectSettings.getParam(ProjectParams.SELECTED_LINE_FONT_COLOR)));
        }
        return component;
    }
}
