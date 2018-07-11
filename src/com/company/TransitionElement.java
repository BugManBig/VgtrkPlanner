package com.company;

public class TransitionElement {
    private Chrono startTime;
    private Chrono endTime;
    private Chrono transitionTime;
    private SelectedDays selectedDays;
    private SelectedDoubles selectedDoubles;

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

    private static final String SPLITTER = "    ";

    public TransitionElement(Chrono startTime, Chrono endTime, Chrono transitionTime,
                             SelectedDays selectedDays, SelectedDoubles selectedDoubles) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.transitionTime = transitionTime;
        this.selectedDays = selectedDays;
        this.selectedDoubles = selectedDoubles;
    }
    
    public String getDataString() {
        return connectViaSplitter(SPLITTER,
                startTime.getTimeString(),
                endTime.getTimeString(),
                selectedDays.getDaysString(),
                transitionTime.getTimeString(),
                selectedDoubles.getDoublesString());
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
