module com.example.marunproject {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.marunproject to javafx.fxml;
    exports com.example.marunproject;
}