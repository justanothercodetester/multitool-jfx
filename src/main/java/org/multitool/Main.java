package org.multitool;

import atlantafx.base.theme.PrimerDark;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        // Apply modern theme
        Application.setUserAgentStylesheet(new PrimerDark().getUserAgentStylesheet());

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/main.fxml"));

        Scene scene = new Scene(loader.load());

        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/styles.css")).toExternalForm());

        stage.setTitle("Multitool");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setOnCloseRequest(e -> {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Are you sure you wish to exit multitool?");
            alert.setTitle("Confirmation");
            alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);

            Optional<ButtonType> result = alert.showAndWait();

            e.consume();

            if (result.isPresent() && result.get() == ButtonType.YES) {
                stage.close();
            }
        });
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}