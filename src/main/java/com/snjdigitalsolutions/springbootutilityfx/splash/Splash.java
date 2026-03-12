package com.snjdigitalsolutions.springbootutilityfx.splash;

import javafx.animation.FadeTransition;
import javafx.application.Preloader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class Splash extends Preloader {

    private static Stage splashStage;
    private SplashController splashController;

    public record CustomProgressNotification(double progress, String message) implements PreloaderNotification {}

    @Override
    public void start(Stage stage) throws Exception {
        splashStage = stage;
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/fxml/Splash.fxml")
        );
        Parent root = loader.load();
        splashController = loader.getController();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.centerOnScreen();
        stage.show();
    }

    @Override
    public void handleApplicationNotification(PreloaderNotification info) {
        // Handle progress updates sent from your main Application
        if (info instanceof ProgressNotification pn) {
            splashController.updateProgress(pn.getProgress(), "Loading...");
        }

        // Handle custom notifications with a message
        if (info instanceof CustomProgressNotification cpn) {
            splashController.updateProgress(cpn.progress(), cpn.message());
        }
    }

    @Override
    public void handleStateChangeNotification(StateChangeNotification info) {
        if (info.getType() == StateChangeNotification.Type.BEFORE_START) {
            // Fade out instead of abruptly hiding
            FadeTransition fade = new FadeTransition(Duration.millis(500), splashStage.getScene().getRoot());
            fade.setFromValue(1.0);
            fade.setToValue(0.0);
            fade.setOnFinished(e -> {
                splashStage.hide();
                // Preconfigurations here
                splashController.showApplicationStage();
            });
            fade.play();
        }
    }


    public static void hideStage() {
        splashStage.hide();
    }
}
