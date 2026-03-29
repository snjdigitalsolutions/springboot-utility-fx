package com.snjdigitalsolutions.springbootutilityfx.node.utility;

import javafx.fxml.FXMLLoader;
import org.springframework.core.io.Resource;

import java.io.IOException;

public class NodeLoader {

    public static void load(Resource fxml, Object node) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(fxml.getURL());
            fxmlLoader.setRoot(node);
            fxmlLoader.setController(node);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
