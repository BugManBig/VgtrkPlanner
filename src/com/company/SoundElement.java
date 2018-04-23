package com.company;

import java.io.Serializable;

public class SoundElement implements Serializable {
    private int mainTime;
    private int chronoTime;
    private String title;
    private boolean[] weekDays;

    public SoundElement(int mainTime, int chronoTime, String title, boolean[] weekDays) {
        this.mainTime = mainTime;
        this.chronoTime = chronoTime;
        this.title = title;

        this.weekDays = weekDays;
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
