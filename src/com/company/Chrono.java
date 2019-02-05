package com.company;

import java.io.Serializable;

public class Chrono implements Serializable {
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

    public String getHoursString() {
        return getTwoDigitsNumber(hours);
    }

    public String getMinutesString() {
        return getTwoDigitsNumber(minutes);
    }

    public String getSecondsString() {
        return getTwoDigitsNumber(seconds);
    }

    public Chrono(int hours, int minutes, int seconds) {
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
    }
    
    public Chrono(int timeInSeconds) {
        timeInSeconds = timeInSeconds % (24 * 60 * 60);
        hours = timeInSeconds / 60 / 60;
        minutes = (timeInSeconds - hours * 60 * 60) / 60;
        seconds = timeInSeconds - hours * 60 * 60 - minutes * 60;
    }
    
    public int getTimeInSeconds() {
        return hours * 60 * 60 + minutes * 60 + seconds;
    }

    public String getTimeString() {
        return getTwoDigitsNumber(hours) + ":" + getTwoDigitsNumber(minutes) + ":" + getTwoDigitsNumber(seconds);
    }
    
    public String getTimeStringSmall() {
        return getTwoDigitsNumber(hours) + ":" + getTwoDigitsNumber(minutes);
    }
    
    private String getTwoDigitsNumber(int number) {
        return number > 9 ? String.valueOf(number) : "0" + number;
    }
}
