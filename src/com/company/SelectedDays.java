package com.company;

public class SelectedDays {
    private boolean[] selectedDays = new boolean[7];

    public SelectedDays(boolean[] selectedDays) {
        this.selectedDays = selectedDays;
    }

    public String getDaysString() {
        String string = "";
        for (int i = 0; i < 7; i++) {
            string += selectedDays[i] ? "X" : "_";
            string += " ";
        }
        return string;
    }
    
    public boolean[] getSelectedDays() {
        return selectedDays;
    }
}
