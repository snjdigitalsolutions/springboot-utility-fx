package com.snjdigitalsolutions.springbootutilityfx.splash;

import javafx.application.Application;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.*;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

public class SplashConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(SplashConfiguration.class);
    private static SplashConfiguration instance;

    private String applicationName;
    private Stage applicationStage;

    private SplashConfiguration(){

    }

    public static SplashConfiguration getInstance() {
        if (instance == null){
            instance = new SplashConfiguration();
        }
        return instance;
    }

    public String getApplicationName() {
        return applicationName;
    }

    /**
     * The application name must be set as the first method call
     * in the SpringBootApplication annotated class used to start
     * the SpringBot application. By making this call, the system
     * javafx.preloader property also gets set
     * @param applicationName the name of the application
     */
    public void setApplicationName(String applicationName) {
        System.setProperty("javafx.preloader", Splash.class.getName());
        this.applicationName = applicationName;
    }

    public Stage getApplicationStage() {
        return applicationStage;
    }

    public void setApplicationStage(Stage applicationStage) {
        this.applicationStage = applicationStage;
    }

    public ApplicationListener<ApplicationEvent> springEventListener(Application application) {
        return event -> {
            if (event instanceof ApplicationStartingEvent) {
                application.notifyPreloader(new Splash.CustomProgressNotification(
                        0.1, "Spring starting..."));
                LOGGER.info("Spring starting");

            } else if (event instanceof ApplicationEnvironmentPreparedEvent) {
                application.notifyPreloader(new Splash.CustomProgressNotification(
                        0.3, "Environment prepared..."));
                LOGGER.info("Environment prepared");

            } else if (event instanceof ApplicationContextInitializedEvent) {
                application.notifyPreloader(new Splash.CustomProgressNotification(
                        0.5, "Context initialized..."));
                LOGGER.info("Context initialized...");

            } else if (event instanceof ApplicationPreparedEvent) {
               application.notifyPreloader(new Splash.CustomProgressNotification(
                        0.7, "Context prepared..."));
                LOGGER.info("Context Prepared");

            } else if (event instanceof ContextRefreshedEvent) {
               application.notifyPreloader(new Splash.CustomProgressNotification(
                        0.9, "Context refreshed..."));
                LOGGER.info("Context Refreshed");

            } else if (event instanceof ApplicationReadyEvent) {
               application.notifyPreloader(new Splash.CustomProgressNotification(
                        1.0, "Ready!"));
                LOGGER.info("Ready!");
            }
        };
    }
}
