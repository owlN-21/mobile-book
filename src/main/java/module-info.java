module com.example.book {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;

    opens com.example.book.model to com.google.gson, javafx.base;
    opens com.example.book.controller to javafx.fxml;

    exports com.example.book;
    exports com.example.book.controller;
}
