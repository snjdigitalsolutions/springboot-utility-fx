# Creating a JavaFX SpringBoot Application  

Using this library for creating a SpringBoot JavaFX application requires 
some initial setup. However, once the setup is completed, you will have the
foundation of the JavaFX application set along with a basic splash view to
inform the user the application in starting up. Complete the following steps:  

- Create base application using spring initializers
- Include the springboot-utility-fx library
```xml
<dependency>  
    <groupId>com.snjdigitalsolutions</groupId>  
    <artifactId>springboot-utility-fx</artifactId>  
    <version>1.0.0-SNAPSHOT</version>  
</dependency>
```
- Modify the class annotated with @SpringBootApplication such that is implements FxBoot. 
Usually placed in the root package.
- Create a class which extends AbstractFXApplication found in the utility library. Usually
placed in the root package.
- Create a Configuration class annotated with @ComponentScan for the application. Usually placed
in a package called configuration
- Add @Import to the SpringBootApplication class and import the following two configurations
    - SpringBootUtilityConfiguration.class
    - The configuration class created for the application in previous steps
- In the main method of the SpringBootApplication class add the following
```java
SplashConfiguration.getInstance().setApplicationName("LabLens");  
ApplicationPreConfiguration.getInstance().setStageWidth(1000);  
ApplicationPreConfiguration.getInstance().setStageHeight(800);  
Toolkit.getDefaultToolkit();
Application.launch(LabLensFX.class, args);
```
- For the changed made in the main method:
  - Setting the application name is necessary for the splash screen otherwise you will get the default "Application Name"
  - Setting the stage width and height is only necessary if you want to change the size of the base stage from its original design
  - Adding the toolkit is also a must
  - For the launch method you will set the class to the class which implements the FxBoot interface
- In the class extending AbstractFXApplication you should have just one method. In this method set the class to the SpringBootApplication class
```java
@Override  
public void init() throws Exception {  
    initialize(createInitializer(this), LablensFxBoot.class);  
}
```
- Create a @Component controller class in the application. This controller is the controller for the base stage. The controller class should be defined in the FXML for the view.
  As you can see below, the controller class allows injecting any beans needed and provides methods needed for the base stage.
```java
@Component  
public class StageReadyController {  
  
    @FXML  
    private BorderPane rootPane;  
  
    private final HostPane hostPane;  
  
    public StageReadyController(HostPane hostPane) {  
        this.hostPane = hostPane;  
    }  
  
    public void addHostPane(){  
        rootPane.setLeft(hostPane);  
    }  
  
}
```
- Create a @Component class which extends AbstractReadyListener. This class, along with the controller, form the base of the application.
    - Use the listener to inject the base FXML and controller class
    - In the performInitialization() method, perform any set up needed before the application stage shows.
```java
@Component  
public class StageReadyListener extends AbstractStageReadyListener {  
  
    private final StageReadyController stageReadyController;  
  
    public StageReadyListener(@Value("classpath:/fxml/RootPane.fxml") Resource fxml,  
                              StageReadyController stageReadyController){  
        super(fxml);  
        this.stageReadyController = stageReadyController;  
    }  
  
    @Override  
    public void performIntialization() {  
        stageReadyController.addHostPane();  
    }  
}
```

With this setup, the application is ready to be executed. The application stage should show after the splash screen fades away.
