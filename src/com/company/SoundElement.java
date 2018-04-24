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

    public int get(TimeNames timeType, TimeNames timeUnit) {
        int time;
        if (timeType == TimeNames.MAIN_TIME) {
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

    public void setMainTime(int mainTime) {
        this.mainTime = mainTime;
    }

    public int getChronoTime() {
        return chronoTime;
    }

    public void setChronoTime(int chronoTime) {
        this.chronoTime = chronoTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean[] getWeekDays() {
        return weekDays;
    }

    public void setWeekDays(boolean[] weekDays) {
        this.weekDays = weekDays;
    }
}
