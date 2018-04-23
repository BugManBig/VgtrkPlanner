package com.company;

import java.io.*;

public class Application {
    private static final String PATH = "D:/AMyasnikov/GitHub/base.bin";

    public void run() {
        FedTimetable fedTimetable = new FedTimetable(this);
        fedTimetable.setDatabase(getDatabaseFromFile());
        fedTimetable.create();
    }

    private PlaylistDatabase getDatabaseFromFile() {
        File file = new File(PATH);
        if (!file.exists()) {
            return new PlaylistDatabase();
        }
        return deserialize();
    }

    public void serialize(PlaylistDatabase playlistDatabase) {
        try {
            FileOutputStream fos = new FileOutputStream(PATH);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(playlistDatabase);
            oos.flush();
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private PlaylistDatabase deserialize() {
        PlaylistDatabase importedDatabase = null;
        try {
            FileInputStream fis = new FileInputStream(PATH);
            ObjectInputStream oin = new ObjectInputStream(fis);
            importedDatabase = (PlaylistDatabase) oin.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return importedDatabase;
    }
}
