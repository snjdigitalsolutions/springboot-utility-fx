package com.snjdigitalsolutions.springbootutilityfx.splash;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;

public class SplashController {

    @FXML
    private Label applicationLabel;
    @FXML
    private ProgressBar progressBar;
    @FXML
    private Label progressStatusLabel;

    private static Stage applicationStage;

    @FXML
    public void initialize() {

    }

    public void updateProgress(double progress, String message) {
        progressBar.setProgress(progress);
        progressStatusLabel.setText(message);
    }

    public static void setStage(Stage stage) {
        applicationStage = stage;
    }

    public void showApplicationStage(){
        applicationStage.show();
    }

}
