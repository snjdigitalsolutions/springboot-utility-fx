package com.snjdigitalsolutions.springbootutilityfx.configuration;


public class ApplicationPreConfiguration {

    private static ApplicationPreConfiguration instance;

    private double stageWidth = 800;
    private double stageHeight = 600;
    private String cssPath = "";

    private ApplicationPreConfiguration(){}

    public static ApplicationPreConfiguration getInstance(){
        if (instance == null){
            instance = new ApplicationPreConfiguration();
        }
        return instance;
    }

    public double getStageWidth() {
        return stageWidth;
    }

    public void setStageWidth(double stageWidth) {
        this.stageWidth = stageWidth;
    }

    public double getStageHeight() {
        return stageHeight;
    }

    public void setStageHeight(double stageHeight) {
        this.stageHeight = stageHeight;
    }

    public String getCssPath() {
        return cssPath;
    }

    public void setCssPath(String cssPath) {
        this.cssPath = cssPath;
    }
}
