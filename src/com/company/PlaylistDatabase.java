package com.company;

import java.util.ArrayList;
import java.util.List;

public class PlaylistDatabase {
    private List<SoundElement> soundElements = new ArrayList<>();

    public void add(SoundElement soundElement) {
        soundElements.add(soundElement);
    }

    public void remove(int id) {
        soundElements.remove(id);
    }

    public int getSize() {
        return soundElements.size();
    }

    public String getPreparedString(int id) {
        SoundElement soundElement = soundElements.get(id);
        String result = getStandardTime(soundElement.getMainTime()) + "    "
                + getStandardTime(soundElement.getChronoTime()) + "    "
                + getWeekString(soundElement.getWeekDays()) + "   " + soundElement.getTitle();
        return result;
    }

    private String getStandardTime(int timeInSeconds) {
        int hours = timeInSeconds / 60 / 60;
        int minutes = (timeInSeconds - hours * 60 * 60) / 60;
        int seconds = timeInSeconds - minutes * 60 - hours * 60 * 60;
        return getTwoDigitString(hours) + ":" + getTwoDigitString(minutes) + ":" + getTwoDigitString(seconds);
    }

    private String getTwoDigitString(int number) {
        if (number < 10) {
            return "0" + number;
        }
        return String.valueOf(number);
    }

    private String getWeekString(boolean[] weekDays) {
        String[] days = {"Mon", "Tue", "Wen", "Thu", "Fri", "Sat", "Sun"};
        String result = "";
        for (int i = 0; i < 7; i++) {
            if (weekDays[i]) {
                result += days[i] + " ";
            } else {
                result += "--- ";
            }
        }
        return result;
    }
}
