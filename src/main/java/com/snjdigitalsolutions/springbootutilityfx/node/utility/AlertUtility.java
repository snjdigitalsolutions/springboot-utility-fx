package com.snjdigitalsolutions.springbootutilityfx.node.utility;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import org.springframework.stereotype.Component;

@Component
public class AlertUtility {

    private final StageLogoUtility stageLogoUtility;

    public AlertUtility(StageLogoUtility stageLogoUtility) {
        this.stageLogoUtility = stageLogoUtility;
    }

    public void informationAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        showAlert(alert, title, message);
    }

    public void warningAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        showAlert(alert, title, message);
    }

    public void confirmAlert(String title, String message, Runnable yesAction) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.setHeaderText(null);
        stageLogoUtility.addLogoToAlert(alert);
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.YES) {
                yesAction.run();
            } else {
                alert.close();
            }
        });
    }

    private void showAlert(Alert alert, String title, String message) {
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        stageLogoUtility.addLogoToAlert(alert);
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                alert.close();
            }
        });
    }


}
