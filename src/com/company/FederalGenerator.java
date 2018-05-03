package com.company;

public class FederalGenerator {
    private PlaylistDatabase playlistDatabase;
    private PlaylistDatabase federalPlaylistDatabase;

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
