package com.company;

public class Chrono {
    private int hours;
    private int minutes;

    public Chrono(int hours, int minutes) {
        this.hours = hours;
        this.minutes = minutes;
    }

    public String getTime() {
        return hours + ":" + minutes;
    }
}
