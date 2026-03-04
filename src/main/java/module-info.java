module org.example.cob {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires com.google.common;

    opens org.example.cob to javafx.fxml;
    opens org.example.cob.eventbus to com.google.common;
    opens org.example.cob.handlers to com.google.common;
    exports org.example.cob;
}