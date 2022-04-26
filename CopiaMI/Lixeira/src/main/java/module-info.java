module com.example.lixeira {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.lixeira to javafx.fxml;
    exports com.example.lixeira;
}