package com.snjdigitalsolutions.springbootutilityfx.node.utility;

import javafx.scene.control.Tooltip;
import javafx.util.Duration;
import org.springframework.stereotype.Component;

@Component
public class TooltipGenerator {

    public Tooltip generateTooltip(String text, Double showDelay) {
        Tooltip tooltip = new Tooltip(text);
        tooltip.setShowDelay(new Duration(showDelay));
        return tooltip;
    }

    public Tooltip generateTooltip(String text) {
        return generateTooltip(text, 350D);
    }

}
