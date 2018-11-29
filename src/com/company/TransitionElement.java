package com.company;

import java.io.Serializable;

public class TransitionElement implements Serializable {
    private Chrono startTime;
    private Chrono endTime;
    private Chrono transitionTime;
    private SelectedDays selectedDays;
    private SelectedDoubles selectedDoubles;
    private int offsetToWeekday;
    private static final String SPLITTER = "    ";

    public Chrono getStartTime() {
        return startTime;
    }

    public Chrono getEndTime() {
        return endTime;
    }

    public Chrono getTransitionTime() {
        return transitionTime;
    }

    public SelectedDays getSelectedDays() {
        return selectedDays;
    }

    public SelectedDoubles getSelectedDoubles() {
        return selectedDoubles;
    }

    public int getOffsetToWeekday() {
        return offsetToWeekday;
    }

    public TransitionElement(Chrono startTime, Chrono endTime, Chrono transitionTime,
                             SelectedDays selectedDays, SelectedDoubles selectedDoubles, int offsetToWeekday) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.transitionTime = transitionTime;
        this.selectedDays = selectedDays;
        this.selectedDoubles = selectedDoubles;
        this.offsetToWeekday = offsetToWeekday;
    }
    
    public String getDataString() {
        String offsetAdding = "";
        if (offsetToWeekday > -1) {
            offsetAdding = "день на дублях: " + DaysOfWeek.values()[offsetToWeekday].toString().toLowerCase();
        }
        return connectViaSplitter(
                startTime.getTimeString(),
                endTime.getTimeString(),
                selectedDays.getDaysString(),
                transitionTime.getTimeString(),
                selectedDoubles.getDoublesString(),
                offsetAdding);
    }

    private String connectViaSplitter(String ... data) {
        String result = "";
        for (int i = 0; i < data.length - 1; i++) {
            result += data[i] + SPLITTER;
        }
        result += data[data.length - 1];
        return result;
    }
}
