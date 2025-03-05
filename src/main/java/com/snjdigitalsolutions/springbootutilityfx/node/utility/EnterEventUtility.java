package com.snjdigitalsolutions.springbootutilityfx.node.utility;

import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import org.springframework.stereotype.Component;

@Component
public class EnterEventUtility {

    public void addEnterListener(Node node, Runnable runnable) {
        node.setOnKeyReleased(event -> {
            if (event.getCode().equals(KeyCode.ENTER)){
                runnable.run();
            }
        });
    }

}
