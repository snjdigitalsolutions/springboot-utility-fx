package com.snjdigitalsolutions.springbootutilityfx.node.utility;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

@Component
public class NodeUtility {

    public void closeNode(ActionEvent event) {
        ((Stage)((Node)event.getSource()).getScene().getWindow()).close();
    }

}
