package com.snjdigitalsolutions.springbootutilityfx.node.utility;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

@Component
public class MinimizeUtility {

    private final KeyCombination keyCombination = new KeyCodeCombination(KeyCode.M, KeyCombination.SHORTCUT_DOWN);

    public void addMinimizeToScene(Scene scene, Stage stage) {

        scene.setOnKeyPressed(event -> {
            if (keyCombination.match(event)) {
                stage.setIconified(true);
                event.consume();
            }
        });

    }
}
