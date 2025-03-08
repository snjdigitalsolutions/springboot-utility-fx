package com.snjdigitalsolutions.springbootutilityfx.node.utility;

import com.snjdigitalsolutions.springbootutilityfx.node.PostShowActionNode;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class StageNodeBuilder {

    private Scene nodeScene;
    private Modality modality;
    private Boolean resizable;
    private String title;
    private Node parentNode;

    private StageNodeBuilder() {

    }

    public static StageNodeBuilder builder() {
        return new StageNodeBuilder();
    }

    public StageNodeBuilder setNode(Parent node) {
        this.parentNode = node;
        if (node.getScene() == null) {
            this.nodeScene = new Scene(node);
        } else {
            this.nodeScene = node.getScene();
        }
        return this;
    }

    public StageNodeBuilder setModality(Modality modality) {
        this.modality = modality;
        return this;
    }

    public StageNodeBuilder setResizable(Boolean resizable) {
        this.resizable = resizable;
        return this;
    }

    public StageNodeBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public Stage build() {
        Stage stage = new Stage();
        stage.setScene(nodeScene);
        if (resizable != null) {
            stage.setResizable(resizable);
        }
        if (modality != null) {
            stage.initModality(modality);
        }
        if (title != null) {
            stage.setTitle(title);
        }
        try {
            StageLogoUtility.addLogoToStage(stage);
        } catch (IOException e) {
            System.out.println("Unable to add logo to stage");
        }
        return stage;
    }

    public void buildAndShow() {
        build().show();
        if (parentNode instanceof PostShowActionNode) {
            ((PostShowActionNode)parentNode).onAfterShow();
        }
    }

}
