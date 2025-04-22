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

    public static Builder builder() {
        Builder builder = new Builder();
        builder.showDelay(350D);
        return builder;
    }

    public static class Builder {

        private final Tooltip tooltip = new Tooltip();

        public Builder text(String text){
            tooltip.setText(text);
            return this;
        }

        public Builder showDelay(Double delay){
            tooltip.setShowDelay(new Duration(delay));
            return this;
        }

        public Builder fontSize(Integer size){
            tooltip.setStyle(String.format("-fx-font-size: %dpx", size));
            return this;
        }

        public Tooltip build(){
            return tooltip;
        }
    }

}
