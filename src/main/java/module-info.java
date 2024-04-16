module com.example.lele {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.example.lele to javafx.fxml;
    exports com.example.lele;
}
