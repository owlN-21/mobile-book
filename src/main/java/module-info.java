module com.example.book {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson; // Если используете JSON

    opens com.example.book.controller to javafx.fxml;
    opens com.example.book.model to javafx.fxml;
    exports com.example.book;
}