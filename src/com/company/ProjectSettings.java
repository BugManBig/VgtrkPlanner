package com.company;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

public class ProjectSettings {
    private static Map<String, String> config = new HashMap<>();

    public static void createMap() {
        String path = null;
        try {
            path = new File(ProjectSettings.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getPath();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        path = path.substring(0, path.lastIndexOf("\\") + 1) + "config.ini";

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
            String currentLine = bufferedReader.readLine();
            while (currentLine != null) {
                parseLine(currentLine);
                currentLine = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void parseLine(String line) {
        int splitterIndex = line.indexOf("=");
        config.put(line.substring(0, splitterIndex).toLowerCase(), line.substring(splitterIndex + 1));
    }

    public static String getParam(ProjectParams key) {
        return config.get(key.toString().toLowerCase());
    }
}
