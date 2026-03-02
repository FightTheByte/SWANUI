module org.cob.cob {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;

    opens org.cob.cob to javafx.fxml;
    exports org.cob.cob;
}