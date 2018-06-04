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
        return connectViaSplitter("    ", startTime.getTime(), lengthTime.getTime(), selectedDays.getDays(), title);
    }
    
    private String connectViaSplitter(String splitter, String ... data) {
        String result = "";
        for (int i = 0; i < data.length - 1; i++) {
            result += data[i] + splitter;
        }
        result += data[data.length - 1];
        return result;
    }
}
