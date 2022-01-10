module com.example.marunproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires jdk.jdi;
    requires java.sql;

    opens com.example.marunproject to javafx.fxml;
    exports com.example.marunproject;
    exports com.example.marunproject.WarningWindows;
    opens com.example.marunproject.WarningWindows to javafx.fxml;
}