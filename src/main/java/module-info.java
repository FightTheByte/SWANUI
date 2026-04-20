module org.example.cob {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires com.google.common;
    requires java.sql;
    requires java.json;
    requires com.opencsv;
    opens org.example.cob to javafx.fxml, com.google.common;
    opens org.example.cob.eventbus to com.google.common;
    opens org.example.cob.handlers to com.google.common;
    exports org.example.cob;
}