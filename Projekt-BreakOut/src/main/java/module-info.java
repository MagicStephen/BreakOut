module com.example.projektbreakout {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.projektbreakout to javafx.fxml;
    exports com.example.projektbreakout;
}