package com.snjdigitalsolutions.springbootutilityfx.application;

import com.snjdigitalsolutions.springbootutilityfx.event.StageReadyEvent;
import com.snjdigitalsolutions.springbootutilityfx.splash.SplashConfiguration;
import javafx.application.Application;
import javafx.application.HostServices;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

/**
 * An abstract class for JavaFX application. Projects using SpringBootUtilityFX
 * library should have a base FX class which extends this class.
 */
public abstract class AbstractFXApplication extends Application {

    protected ConfigurableApplicationContext springContext;

    /**
     * Initialize the SpringContext using the SpringApplicationBuilder. You must call this
     * method creating an initializer using the FX Application class and passing in the
     * SpringBoot application class.
     * @param initializer application context initializer
     * @param bootClass the SpringBoot class
     * @throws Exception
     */
    protected void initialize(ApplicationContextInitializer<GenericApplicationContext> initializer, Class<? extends FxBoot> bootClass) throws Exception {

        springContext = new SpringApplicationBuilder()
                .sources(bootClass)
                .initializers(initializer)
                .listeners(SplashConfiguration.getInstance().springEventListener(this))
                .run(getParameters().getRaw().toArray(new String[0]));
    }

    public abstract void init() throws Exception;

    @Override
    public void start(Stage primaryStage) throws Exception {
        springContext.publishEvent(new StageReadyEvent(primaryStage));
    }

    @Override
    public void stop() throws Exception {
        springContext.close();
        Platform.exit();
    }

    protected static ApplicationContextInitializer<GenericApplicationContext> createInitializer(Application app) {
        return applicationContext -> {
            applicationContext.registerBean(Application.class, () -> app);
            applicationContext.registerBean(Parameters.class, app::getParameters);
            applicationContext.registerBean(HostServices.class, app::getHostServices);
        };
    }
}
