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

    public String getTime() {
        return getTwoDigitsNumber(hours) + ":" + getTwoDigitsNumber(minutes) + ":" + getTwoDigitsNumber(seconds);
    }
    
    private String getTwoDigitsNumber(int number) {
        return number > 9 ? String.valueOf(number) : "0" + number;
    }
}
