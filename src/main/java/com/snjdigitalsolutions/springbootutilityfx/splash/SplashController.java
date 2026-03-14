package com.snjdigitalsolutions.springbootutilityfx.splash;

import com.snjdigitalsolutions.springbootutilityfx.node.SpringInitializableNode;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;

import java.util.Map;

public class SplashController {

    @FXML
    private Label applicationLabel;
    @FXML
    private ProgressBar progressBar;
    @FXML
    private Label progressStatusLabel;

    private static Stage applicationStage;
    private static ApplicationContext applicationContext;

    @FXML
    public void initialize() {
        applicationLabel.setText(SplashConfiguration.getInstance().getApplicationName());
    }

    public void updateProgress(double progress, String message) {
        progressBar.setProgress(progress);
        progressStatusLabel.setText(message);
    }

    public static void setStage(Stage stage, ApplicationContext context) {
        applicationStage = stage;
        applicationContext = context;
    }

    public void showApplicationStage(){
        Map<String, SpringInitializableNode> initializableNodes = applicationContext.getBeansOfType(SpringInitializableNode.class);
        initializableNodes.values().forEach(SpringInitializableNode::performIntialization);
        applicationStage.show();
    }

}
