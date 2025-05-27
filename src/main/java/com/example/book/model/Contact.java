package com.example.book.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Contact {
    // Для сериализации используем обычные строки
    private String name;
    private String phone;
    private String email;

    // JavaFX Properties (только для UI)
    private transient StringProperty nameProperty;
    private transient StringProperty phoneProperty;
    private transient StringProperty emailProperty;

    public Contact() {
        this("", "", "");
    }

    public Contact(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        initProperties();
    }

    private void initProperties() {
        this.nameProperty = new SimpleStringProperty(name);
        this.phoneProperty = new SimpleStringProperty(phone);
        this.emailProperty = new SimpleStringProperty(email);
    }

    // Геттеры/сеттеры для обычных полей (используются Gson)
    public String getName() { return name; }
    public void setName(String name) {
        this.name = name;
        if (nameProperty != null) nameProperty.set(name);
    }

    // Геттеры для JavaFX Properties
    public StringProperty nameProperty() {
        if (nameProperty == null) initProperties();
        return nameProperty;
    }

    public StringProperty phoneProperty() {
        if (phoneProperty == null) initProperties();
        return phoneProperty;
    }

    public StringProperty emailProperty() {
        if (emailProperty == null) initProperties();
        return emailProperty;
    }
}