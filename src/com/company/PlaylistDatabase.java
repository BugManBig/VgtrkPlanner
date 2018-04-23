package com.company;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PlaylistDatabase implements Serializable {
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

    public SoundElement get(int id) {
        return soundElements.get(id);
    }

    public void set(int id, SoundElement soundElement) {
        soundElements.set(id, soundElement);
    }

    public String getPreparedString(int id) {
        SoundElement soundElement = soundElements.get(id);
        return getStandardTime(soundElement, true) + "    "
                + getStandardTime(soundElement, false) + "    "
                + getWeekString(soundElement.getWeekDays()) + "   " + soundElement.getTitle();
    }

    private String getStandardTime(SoundElement soundElement, boolean isMain) {
        if (isMain) {
            return getTwoDigitString(soundElement.get(TimeNames.MAIN_TIME, TimeNames.HOURS)) + ":"
                    + getTwoDigitString(soundElement.get(TimeNames.MAIN_TIME, TimeNames.MINUTES))
                    + ":" + getTwoDigitString(soundElement.get(TimeNames.MAIN_TIME, TimeNames.SECONDS));
        }
        return getTwoDigitString(soundElement.get(TimeNames.CHRONO_TIME, TimeNames.HOURS)) + ":"
                + getTwoDigitString(soundElement.get(TimeNames.CHRONO_TIME, TimeNames.MINUTES))
                + ":" + getTwoDigitString(soundElement.get(TimeNames.CHRONO_TIME, TimeNames.SECONDS));
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
