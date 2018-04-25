package com.company;

import java.util.ArrayList;

public class FederalGenerator {
    private PlaylistDatabase playlistDatabase;
    private PlaylistDatabase federalPlaylistDatabase;
    private ArrayList<String> federalTimetable = new ArrayList<>();

    public FederalGenerator(PlaylistDatabase federalPlaylistDatabase, PlaylistDatabase playlistDatabase) {
        this.federalPlaylistDatabase = federalPlaylistDatabase;
        this.playlistDatabase = playlistDatabase;
    }

    public void generate() {
        SoundElement soundElement;
        for (int dayOfWeek = 0; dayOfWeek < 7; dayOfWeek++) {
            for (int i = 0; i < playlistDatabase.getSize(); i++) {
                soundElement = playlistDatabase.get(i);
                if (soundElement.getWeekDays()[dayOfWeek]) {
                    federalPlaylistDatabase.add(soundElement);
                }
            }
        }
    }
}
