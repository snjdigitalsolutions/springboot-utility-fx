package com.snjdigitalsolutions.springbootutilityfx.node.utility;

import javafx.scene.control.Button;
import org.springframework.stereotype.Component;

@Component
public class ButtonUtility {

    public void setAction(Button button, Runnable action) {
        button.setOnAction(event -> {
            action.run();
        });
    }

}
