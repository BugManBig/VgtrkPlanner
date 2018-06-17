package com.company;

public class Chrono {
    private int hours;
    private int minutes;
    private int seconds;

    public int getHours() {
        return hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getSeconds() {
        return seconds;
    }

    public Chrono(int hours, int minutes, int seconds) {
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
    }
    
    public int getTimeInSeconds() {
        return hours * 60 * 60 + minutes * 60 + seconds;
    }

    public String getTimeString() {
        return getTwoDigitsNumber(hours) + ":" + getTwoDigitsNumber(minutes) + ":" + getTwoDigitsNumber(seconds);
    }
    
    private String getTwoDigitsNumber(int number) {
        return number > 9 ? String.valueOf(number) : "0" + number;
    }
}
