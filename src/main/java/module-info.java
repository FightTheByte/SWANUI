module org.example.cob {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;

    opens org.example.cob to javafx.fxml;
    exports org.example.cob;
}