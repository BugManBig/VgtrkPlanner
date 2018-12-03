package com.company;

import javax.swing.*;
import java.awt.*;

public class SelectedCellRenderer extends DefaultListCellRenderer {
    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        Component component = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        if (isSelected) {
            component.setBackground(Color.decode("#666666"));
            component.setForeground(Color.decode("#DDDDDD"));
        }
        return component;
    }
}
