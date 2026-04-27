package org.multitool;

import javafx.scene.control.*;
import org.libraries.IPFinder;
import org.libraries.InfoBox;
import java.util.Optional;

import javax.swing.*;
import java.io.IOException;

public class Controller {

    public void runRegedit() {
        runAdmin("regedit");
    }

    public void runComputerManager() {
        runAdmin("compmgmt.msc");
    }

    public void runCMD() {
        runAdmin("cmd.exe");
    }

    public void displayAddress() {
        String ipv4 = IPFinder.getIPv4();
        InfoBox.show("IPV4 address", "Your ipv4 address is: " + ipv4, ipv4);
    }

    public void recoveryMode() {
        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmAlert.setTitle("Confirm action");
        confirmAlert.setHeaderText(null);
        confirmAlert.setContentText(
                "Are you sure you want to reboot into the Windows Recovery Environment? Your system will restart."
        );

        confirmAlert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);

        Optional<ButtonType> result = confirmAlert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.YES) {

            Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
            infoAlert.setTitle("");
            infoAlert.setHeaderText(null);
            infoAlert.setContentText("Your system will restart in 5 seconds");
            infoAlert.getButtonTypes().setAll(ButtonType.OK);

            try {
                new ProcessBuilder(
                        "cmd.exe",
                        "/c",
                        "shutdown /r /o /f /t 5"
                ).start();

                infoAlert.show();

            } catch (IOException e) {
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setTitle("Error");
                errorAlert.setHeaderText(null);
                errorAlert.setContentText(
                        "Unable to run command.\n\n" +
                                "To do this manually:\n" +
                                "Press Windows + R and run:\n" +
                                "shutdown /r /o /f /t 0"
                );
                errorAlert.show();
            }
        }
    }

    public void BIOSMode() {
        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmAlert.setTitle("Confirm action");
        confirmAlert.setHeaderText(null);
        confirmAlert.setContentText(
                "Are you sure you want to boot into the BIOS? Your system will shut down and enter the BIOS on the next boot"
        );

        confirmAlert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);

        Optional<ButtonType> result = confirmAlert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.YES) {

            try {
                new ProcessBuilder(
                        "powershell",
                        "-Command",
                        "Start-Process shutdown -ArgumentList '/s /fw /f /t 10' -Verb runAs"
                ).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void runAdmin(String cmd) {
        try {
            new ProcessBuilder(
                    "powershell",
                    "-Command",
                    "Start-Process " + cmd + " -Verb runAs"
            ).start();
        } catch (IOException e) {
            error(cmd);
            e.printStackTrace();
        }
    }

    public void error(String cmd) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error executing action");
        alert.setHeaderText("Unable to run command");
        alert.setContentText("Unable to run the command" + cmd + ". Maybe try reinstalling the program to fix it.");
        alert.showAndWait();
    }
}