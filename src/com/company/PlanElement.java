package com.company;

public class PlanElement {
    private String title;
    private Chrono startTime;
    private Chrono lengthTime;
    private SelectedDays selectedDays;

    public PlanElement(String title, Chrono startTime, Chrono lengthTime, SelectedDays selectedDays) {
        this.title = title;
        this.startTime = startTime;
        this.lengthTime = lengthTime;
        this.selectedDays = selectedDays;
    }

    public String getDataString() {
        return startTime.getTime() + " " + lengthTime.getTime() + " " + selectedDays.getDays() + " " + title;
    }
}
