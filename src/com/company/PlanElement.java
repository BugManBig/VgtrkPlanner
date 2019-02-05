package com.company;

import java.io.Serializable;

public class PlanElement implements Cloneable, Serializable {
    private String title;
    private Chrono startTime;
    private Chrono lengthTime;
    private SelectedDays selectedDays;
    private static final String SPLITTER = "    ";

    public String getTitle() {
        return title;
    }

    public Chrono getStartTime() {
        return startTime;
    }

    public void setStartTime(Chrono startTime) {
        this.startTime = startTime;
    }

    public Chrono getDurationTime() {
        return lengthTime;
    }

    public SelectedDays getSelectedDays() {
        return selectedDays;
    }

    public Chrono getEndTime() {
        return new Chrono(startTime.getTimeInSeconds() + lengthTime.getTimeInSeconds());
    }

    public PlanElement(String title, Chrono startTime, Chrono lengthTime, SelectedDays selectedDays) {
        this.title = title;
        this.startTime = startTime;
        this.lengthTime = lengthTime;
        this.selectedDays = selectedDays;
    }

    public String getDataStringForSetka() {
        return connectViaSplitter(
                startTime.getTimeString(),
                lengthTime.getTimeString(),
                selectedDays.getDaysString(),
                title);
    }
    
    public String getDataStringForFinal() {
        return connectViaSplitter(
                startTime.getTimeString(),
                lengthTime.getTimeString(),
                title);
    }
    
    private String connectViaSplitter(String ... data) {
        String result = "";
        for (int i = 0; i < data.length - 1; i++) {
            result += data[i] + SPLITTER;
        }
        result += data[data.length - 1];
        return result;
    }

    @Override
    protected PlanElement clone() throws CloneNotSupportedException {
        return (PlanElement) super.clone();
    }
}
