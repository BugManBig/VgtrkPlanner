package com.company;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileActions {
    public static void createFile(String path, List<String> data) {
        Path file = Paths.get(path);
        try {
            Files.write(file, data, Charset.forName("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void save(Object object, String path, String name) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(path + "\\" + name);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(object);
            objectOutputStream.flush();
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object load(String path, String name) {
        Object object = null;
        if (!new File(path + "\\" + name).exists()) return null;
        try {
            FileInputStream fileInputStream = new FileInputStream(path + "\\" + name);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            object = objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return object;
    }

    public static boolean isExist(String path, String name) {
        return new File(path + "\\" + name).exists();
    }
}
