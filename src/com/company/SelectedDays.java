package com.company;

import java.io.Serializable;

public class SelectedDays implements Serializable {
    private boolean[] selectedDays;

    public SelectedDays(boolean[] selectedDays) {
        this.selectedDays = selectedDays;
    }

    public String getDaysString() {
        String string = "";
        for (int i = 0; i < 7; i++) {
            string += selectedDays[i] ? DaysOfWeek.values()[i].getShortName() : "---";
            string += " ";
        }
        return string;
    }
    
    public boolean[] getSelectionsArray() {
        return selectedDays;
    }
}
