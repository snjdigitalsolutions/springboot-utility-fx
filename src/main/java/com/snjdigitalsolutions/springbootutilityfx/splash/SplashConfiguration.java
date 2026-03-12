package com.snjdigitalsolutions.springbootutilityfx.splash;

import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.boot.context.event.*;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

public class SplashConfiguration {

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
                System.out.println("Spring starting");

            } else if (event instanceof ApplicationEnvironmentPreparedEvent) {
                application.notifyPreloader(new Splash.CustomProgressNotification(
                        0.3, "Environment prepared..."));
                System.out.println("Environment prepared");

            } else if (event instanceof ApplicationContextInitializedEvent) {
                application.notifyPreloader(new Splash.CustomProgressNotification(
                        0.5, "Context initialized..."));
                System.out.println("Context initialized...");

            } else if (event instanceof ApplicationPreparedEvent) {
               application.notifyPreloader(new Splash.CustomProgressNotification(
                        0.7, "Context prepared..."));
                System.out.println("Context Prepared");

            } else if (event instanceof ContextRefreshedEvent) {
               application.notifyPreloader(new Splash.CustomProgressNotification(
                        0.9, "Context refreshed..."));
                System.out.println("Context Refreshed");

            } else if (event instanceof ApplicationReadyEvent) {
               application.notifyPreloader(new Splash.CustomProgressNotification(
                        1.0, "Ready!"));
                System.out.println("Ready!");
            }
        };
    }
}
