package com.example.book.utill;

import com.example.book.model.Contact;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FileUtil {
    private static final String FILE_NAME = "contacts.json";
    private static final Gson gson = new Gson();

    public static void saveContacts(List<Contact> contacts) {
        try {
            Path path = getDataFilePath();
            Files.createDirectories(path.getParent());

            // Создаем обычный список
            List<Contact> contactList = new ArrayList<>(contacts);

            Files.writeString(path, gson.toJson(contactList));
        } catch (IOException e) {
            System.err.println("Ошибка сохранения: " + e.getMessage());
        }
    }



    public static List<Contact> loadContacts() {
        try {
            Path path = getDataFilePath();
            if (Files.exists(path)) {
                String json = Files.readString(path);
                return gson.fromJson(json, new TypeToken<List<Contact>>(){}.getType());
            }
        } catch (IOException e) {
            System.err.println("Ошибка загрузки: " + e.getMessage());
        }
        return Collections.emptyList();
    }

    private static Path getDataFilePath() {
        return Paths.get(System.getProperty("user.home"), "addressbook", FILE_NAME);
    }
}