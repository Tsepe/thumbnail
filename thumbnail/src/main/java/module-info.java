module com.example.thumbnail {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.thumbnail to javafx.fxml;
    exports com.example.thumbnail;
}