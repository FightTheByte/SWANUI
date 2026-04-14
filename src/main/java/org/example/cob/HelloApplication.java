package org.example.cob;

import com.dlsc.formsfx.model.structure.*;
import com.dlsc.formsfx.view.renderer.FormRenderer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.cob.UI.FormGenerator;
import org.example.cob.util.WriteInputToFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class HelloApplication extends Application {

    Button run = new Button("Run");

    ScrollPane sp = new ScrollPane();

    FormGenerator formGenerator = new FormGenerator("src/main/resources/swan.csv");

    List<Section> sections;

    String[] titles = new String[]{
            "Start-up",
            "Computational Grid",
            "Input Grids and Data",
            "Boundary and Initial Conditions",
            "Physics",
            "Numerics",
            "Output Locations",
            "Computed Quantities",
            "Lock-up"
    };

    @Override
    public void start(Stage stage) throws IOException {

        sections = formGenerator.getSections();
        final int[] i = {0};
        sections.forEach(section ->{
            section.title(titles[i[0]]);
            i[0]++;
        });

        Form form = Form.of(
                sections.toArray(new Section[0])
        );

        FormRenderer renderer = new FormRenderer(form);

        sections.forEach(section -> {
            section.collapse(false);
            section.collapse(true);
        });

        BorderPane interactionPanel= new BorderPane();
        HBox controls = new HBox(10);
        controls.setAlignment(Pos.CENTER);
        controls.setPadding(new Insets(10,10,10,10));

        run.setId("custom-button");
        TextArea console = new TextArea();
        VBox runBox = new VBox(10);

        console.setEditable(false);
        console.setMinHeight(200);
        console.setId("custom-console");
        console.setWrapText(true);

        controls.getChildren().addAll(run);
        runBox.getChildren().addAll(controls, console);
        runBox.setAlignment(Pos.CENTER);

        run.setOnAction(event -> {
            if(form.isValid()) {
                try {
                    WriteInputToFile.writeToFile(form);
                } catch (IOException e) {
                    String previousText = console.getText();
                    console.setText(previousText + "\n" + e);
                }
                String previousText = console.getText();
                console.setText(previousText + "\n" + "Running SWAN...");
            } else {
                String previousText = console.getText();
                console.setText(previousText + "\n" + "Input is invalid, please check for missing inputs");
            }
        });

        sp.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        sp.setFitToWidth(true);
        sp.setContent(renderer);



        interactionPanel.setId("main");
        interactionPanel.setCenter(sp);
        interactionPanel.setBottom(runBox);

        StackPane stackPane = new StackPane(interactionPanel);

        Scene scene = new Scene(stackPane, 1500, 1000);
        scene.getStylesheets().addAll(
                Objects.requireNonNull(getClass().getResource("/org/example/cob/controls.css")).toExternalForm(),
                Objects.requireNonNull(getClass().getResource("/org/example/cob/formStyling.css")).toExternalForm()
        );
        stage.setTitle("COB");
        stage.setScene(scene);
        stage.show();
    }
}
