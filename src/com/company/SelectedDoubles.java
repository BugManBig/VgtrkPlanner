package com.company;

import java.io.Serializable;

public class SelectedDoubles implements Serializable {
    private boolean[] selectedDoubles = new boolean[4];

    public SelectedDoubles(boolean[] selectedDoubles) {
        this.selectedDoubles = selectedDoubles;
    }

    public boolean[] getSelectionsArray() {
        return selectedDoubles;
    }

    public String getDoublesString() {
        String string = "";
        for (int i = 0; i < 4; i++) {
            string += selectedDoubles[i] ? "X" : "_";
            string += " ";
        }
        return string;
    }
}
