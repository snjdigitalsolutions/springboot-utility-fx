package com.snjdigitalsolutions.springbootutilityfx.node.utility;

import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class StageLogoUtility {

    private static Resource stageLogo = null;

    public StageLogoUtility(@Value("classpath:/booklogo.png") Resource logo) {
        stageLogo = logo;
    }

    public static void addLogoToStage(Stage stage) throws IOException {
        stage.getIcons().add(new Image(stageLogo.getURL().toString()));
    }

    public void addLogoToAlert(Alert alert) {
        try {
            ((Stage) alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image(stageLogo.getURL().toString()));
        } catch (IOException e) {
            System.out.println("Cannot add logo to alert");
        }
    }

}
