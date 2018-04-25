package com.company;

import java.io.Serializable;

public class SoundElement implements Serializable {
    private int mainTime;
    private int chronoTime;
    private String title;
    private boolean[] weekDays;
    private int id;

    public SoundElement(int mainTime, int chronoTime, String title, boolean[] weekDays, int id) {
        this.mainTime = mainTime;
        this.chronoTime = chronoTime;
        this.title = title;
        this.weekDays = weekDays;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int get(boolean isMainTime, TimeNames timeUnit) {
        int time;
        if (isMainTime) {
            time = mainTime;
        } else {
            time = chronoTime;
        }
        int hours = time / 60 / 60;
        int minutes = (time - hours * 60 * 60) / 60;
        int seconds = time - minutes * 60 - hours * 60 * 60;
        if (timeUnit == TimeNames.HOURS) {
            return hours;
        } else if (timeUnit == TimeNames.MINUTES) {
            return minutes;
        }
        return seconds;
    }

    public int getMainTime() {
        return mainTime;
    }

    public String getTitle() {
        return title;
    }

    public boolean[] getWeekDays() {
        return weekDays;
    }
}
