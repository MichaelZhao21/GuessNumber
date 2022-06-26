module com.example.demoguessnumber {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.demoguessnumber to javafx.fxml;
    exports com.example.demoguessnumber;
}