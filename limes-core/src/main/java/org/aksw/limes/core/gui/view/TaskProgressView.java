package org.aksw.limes.core.gui.view;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.aksw.limes.core.gui.controller.TaskProgressController;

/**
 * popup window to show progress to the user
 * @author Daniel Obraczka {@literal <} soz11ffe{@literal @}
 *         studserv.uni-leipzig.de{@literal >}
 *
 */
public class TaskProgressView {
    private Stage stage;
    private TaskProgressController controller;
    /**
     * displays what is happening at the moment
     */
    private StringProperty informationLabel;
    private ProgressBar progressBar;

    /**
     * Constructor builds the window with the given title
     * @param title title of window
     */
    public TaskProgressView(String title) {
        showWindow(title);
    }

    /**
     * sets the controller
     * @param controller controller
     */
    public void setController(TaskProgressController controller) {
        this.controller = controller;
    }

    /**
     * builds the window
     * @param title
     */
    private void showWindow(String title) {
        BorderPane mainPane = new BorderPane();

        Label label = new Label(title);
        Label information = new Label("");
        informationLabel = new SimpleStringProperty(" ");
        information.textProperty().bind(informationLabel);

        progressBar = new ProgressBar();

        HBox hb = new HBox();
        hb.setSpacing(5);
        hb.setAlignment(Pos.CENTER);
        hb.getChildren().addAll(label, progressBar);
        VBox vb = new VBox();
        vb.getChildren().addAll(hb, information);
        vb.setAlignment(Pos.CENTER);
        mainPane.setTop(vb);

        Button cancelButton = new Button("Cancel");

        HBox hb2 = new HBox();
        hb2.setSpacing(5);
        hb2.setAlignment(Pos.CENTER);
        hb2.getChildren().addAll(cancelButton);
        mainPane.setBottom(hb2);

        // cancels the mapping and closes the window
        cancelButton.setOnAction(event -> {
            controller.cancel();
        });

        Scene scene = new Scene(mainPane, 250, 60, Color.WHITE);
        scene.getStylesheets().add("gui/main.css");
        stage = new Stage();
        stage.setAlwaysOnTop(true);
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * closes the window
     */
    public void close() {
        stage.close();
    }

    /**
     * returns the informationLabel
     * @return label
     */
    public StringProperty getInformationLabel() {
        return informationLabel;
    }

    /**
     * returns the progress bar
     * @return progress bar
     */
    public ProgressBar getProgressBar() {
        return progressBar;
    }
}
