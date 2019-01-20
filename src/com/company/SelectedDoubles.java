package com.company;

import java.io.Serializable;

public class SelectedDoubles implements Serializable {
    private boolean[] selectedDoubles;

    public SelectedDoubles(boolean[] selectedDoubles) {
        this.selectedDoubles = selectedDoubles;
    }

    public String getDoublesString() {
        String string = "";
        int doublesCount = Integer.parseInt(ProjectSettings.getParam(ProjectParams.DOUBLES_COUNT));
        for (int i = 0; i < doublesCount; i++) {
            string += selectedDoubles[i] ? i + 1 : "-";
            string += " ";
        }
        return string;
    }

    public boolean[] getSelectionsArray() {
        return selectedDoubles;
    }
}
