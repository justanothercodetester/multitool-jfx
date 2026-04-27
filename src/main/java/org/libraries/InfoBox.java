package org.libraries;

import javafx.event.Event;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

public class InfoBox {

    public static void show(String title, String message, String info) {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);

        Label label = new Label(message);

        Button Ok = new Button("Ok");
        Ok.setOnAction(e -> {
            window.close();
        });
        Button Copy = new Button("Copy info");
        Copy.setOnAction(e -> {
            Clipboard cb = Toolkit.getDefaultToolkit().getSystemClipboard();
            StringSelection data = new StringSelection(info);
            cb.setContents(data, null);
        });

        VBox layout = new VBox();
        layout.getChildren().add(label);

        HBox buttons = new HBox(Ok, Copy);
        layout.getChildren().add(buttons);

        layout.setAlignment(Pos.CENTER);
        layout.setSpacing(10);
        buttons.setAlignment(Pos.CENTER);
        buttons.setSpacing(15);

        Scene scene = new Scene(layout, 200, 100);

        window.setScene(scene);
        window.setResizable(false);

        window.showAndWait();
    }
}
