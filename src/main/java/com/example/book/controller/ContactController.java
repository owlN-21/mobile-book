package com.example.book.controller;


import com.example.book.model.Contact;
import com.example.book.utill.FileUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.*;


import java.util.Collections;
import java.util.List;

public class ContactController {
    @FXML private TableView<Contact> contactsTable;
    @FXML private TextField nameField, phoneField, emailField, searchField;

    private final ObservableList<Contact> contacts = FXCollections.observableArrayList();
    private final FilteredList<Contact> filteredContacts = new FilteredList<>(contacts);

    @FXML
    public void initialize() {
        List<Contact> loaded = FileUtil.loadContacts();
        if (loaded != null) {
            contacts.addAll(loaded);
        }
    }

    @FXML
    private void handleAddContact() {
        String name = nameField.getText().trim();
        String phone = phoneField.getText().trim();
        String email = emailField.getText().trim();

        if (name.isEmpty() || phone.isEmpty() || email.isEmpty()) {
            showAlert("Ошибка", "Все поля должны быть заполнены!");
            return;
        }

        contacts.add(new Contact(name, phone, email));
        clearFields();
        FileUtil.saveContacts(contacts); // Сохраняем в файл
    }

    @FXML
    private void handleDeleteContact() {
        Contact selected = contactsTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            contacts.remove(selected);
            FileUtil.saveContacts(contacts);
        } else {
            showAlert("Ошибка", "Выберите контакт для удаления!");
        }
    }

    @FXML
    private void handleSearch() {
        String keyword = searchField.getText().toLowerCase();
        filteredContacts.setPredicate(contact ->
                contact.getName().toLowerCase().contains(keyword)
        );
    }

    private void clearFields() {
        nameField.clear();
        phoneField.clear();
        emailField.clear();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}