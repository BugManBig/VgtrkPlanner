package com.company;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PlaylistDatabase implements Serializable {
    private List<SoundElement> soundElements = new ArrayList<>();
    private int id = 0;

    public void add(SoundElement soundElement) {
        soundElements.add(soundElement);
        sort();
    }

    public int getUniqueId() {
        return id++;
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
        sort();
    }

    public String getPreparedString(int id) {
        SoundElement soundElement = soundElements.get(id);
        return AdditionalUtilities.getStandardTime(soundElement, true) + "    "
                + AdditionalUtilities.getStandardTime(soundElement, false) + "    "
                + getWeekString(soundElement.getWeekDays()) + "   " + soundElement.getTitle();
    }

    private String getWeekString(boolean[] weekDays) {
        WeekDays[] days = WeekDays.values();
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

    private void sort() {
        int minIndex;
        SoundElement buffer;
        for (int i = 0; i < soundElements.size() - 1; i++) {
            minIndex = i;
            for (int j = i + 1; j < soundElements.size(); j++) {
                if (soundElements.get(j).getMainTime() < soundElements.get(minIndex).getMainTime()) {
                    minIndex = j;
                }
            }
            buffer = soundElements.get(i);
            soundElements.set(i, soundElements.get(minIndex));
            soundElements.set(minIndex, buffer);
        }
    }
}
