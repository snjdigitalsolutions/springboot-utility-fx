package com.snjdigitalsolutions.springbootutilityfx.node.utility;

import javafx.scene.control.TextFormatter;
import org.springframework.stereotype.Component;

import java.util.function.UnaryOperator;

@Component
public class TextFieldValidationUtility {

    private final UnaryOperator<TextFormatter.Change> notEmptyValidationFormatter = change -> {
        if (change.getControlNewText().isBlank()) {
            return change;
        }
        return change;
    };

    public UnaryOperator<TextFormatter.Change> notEmptyValidationFormatter() {
        return notEmptyValidationFormatter;
    }

}
