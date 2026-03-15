package com.snjdigitalsolutions.springbootutilityfx.application;

import com.snjdigitalsolutions.springbootutilityfx.configuration.ApplicationPreConfiguration;
import com.snjdigitalsolutions.springbootutilityfx.event.StageReadyEvent;
import com.snjdigitalsolutions.springbootutilityfx.node.SpringInitializableNode;
import com.snjdigitalsolutions.springbootutilityfx.node.utility.MinimizeUtility;
import com.snjdigitalsolutions.springbootutilityfx.splash.SplashController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.Resource;

import java.io.IOException;

/**
 * The stage ready listener fires when the application is ready to start.
 * This happens after Spring has initialized and is ready. The onApplicationEvent
 * method performs common setup of the JavaFX application by creating the base
 * stage.
 */
public abstract class AbstractStageReadyListener implements ApplicationListener<StageReadyEvent> {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractStageReadyListener.class);

    protected final Resource fxml;
    @Autowired
    protected ApplicationContext applicationContext;
    @Autowired
    protected MinimizeUtility minimizeUtility;

    public AbstractStageReadyListener(Resource fxml) {
        this.fxml = fxml;
    }

    @Override
    public void onApplicationEvent(StageReadyEvent event) {
        try {
            Stage applicationStage = event.getStage();
            applicationStage.setOnShown(shown -> {
                //TODO Refactor adding feature to allow for Runnable to be injected
                LOGGER.debug("Application showing...");
            });
            applicationStage.setWidth(ApplicationPreConfiguration.getInstance().getStageWidth());
            applicationStage.setHeight(ApplicationPreConfiguration.getInstance().getStageHeight());
            SplashController.setStage(applicationStage, applicationContext);
            FXMLLoader fxmlLoader = new FXMLLoader(fxml.getURL());
            fxmlLoader.setControllerFactory(applicationContext::getBean);
            Parent applicationRoot = fxmlLoader.load();
            Scene applicationScene = new Scene(applicationRoot);
            applicationStage.setScene(applicationScene);
            if (!ApplicationPreConfiguration.getInstance().getCssPath().isEmpty()) {
                applicationScene.getStylesheets().add(getClass().getResource("/styles/application.css").toExternalForm());
            }
            minimizeUtility.addMinimizeToScene(applicationScene, applicationStage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
