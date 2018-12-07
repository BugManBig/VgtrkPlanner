package com.company;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProjectSettings {
    private static Map<String, String> config = new HashMap<>();

    public static boolean createMap() {
        String path = null;
        try {
            path = new File(ProjectSettings.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getPath();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        path = path.substring(0, path.lastIndexOf("\\") + 1) + "config.ini";
        if (!new File(path).exists()) {
            createIniFile(path);
            return false;
        }

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
            String currentLine = bufferedReader.readLine();
            while (currentLine != null) {
                parseLine(currentLine);
                currentLine = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (getParam(ProjectParams.WINDOW_AUTO_SIZE).equals("true")) {
            config.remove(ProjectParams.WINDOW_WIDTH.toString());
            config.remove(ProjectParams.WINDOW_HEIGHT.toString());
            Dimension resolution = Toolkit.getDefaultToolkit().getScreenSize();
            config.put(ProjectParams.WINDOW_WIDTH.toString().toLowerCase(), String.valueOf(resolution.width - 10));
            config.put(ProjectParams.WINDOW_HEIGHT.toString().toLowerCase(), String.valueOf(resolution.height - 50));
        }

        return true;
    }

    private static void createIniFile(String path) {
        List<String> list = new ArrayList<>();
        list.add(ProjectParams.BIN_PATH + "=");
        list.add(ProjectParams.OUTPUT_PATH + "=");
        list.add(ProjectParams.WINDOW_WIDTH + "=1600");
        list.add(ProjectParams.WINDOW_HEIGHT + "=800");
        list.add(ProjectParams.WINDOW_AUTO_SIZE + "=true");
        list.add(ProjectParams.FONT_SIZE + "=20");
        list.add(ProjectParams.WINDOW_BACKGROUND_COLOR + "=#444444");
        list.add(ProjectParams.WINDOW_FONT_COLOR + "=#DDDDDD");
        list.add(ProjectParams.BUTTON_BACKGROUND_COLOR + "=#555555");
        list.add(ProjectParams.BUTTON_FONT_COLOR + "=#DDDDDD");
        list.add(ProjectParams.SELECTED_LINE_BACKGROUND_COLOR + "=#666666");
        list.add(ProjectParams.SELECTED_LINE_FONT_COLOR + "=#FFFFFF");
        FileActions.createFile(path, list);
    }

    private static void parseLine(String line) {
        int splitterIndex = line.indexOf("=");
        config.put(line.substring(0, splitterIndex).toLowerCase(), line.substring(splitterIndex + 1));
    }

    public static String getParam(ProjectParams key) {
        return config.get(key.toString().toLowerCase());
    }
}
