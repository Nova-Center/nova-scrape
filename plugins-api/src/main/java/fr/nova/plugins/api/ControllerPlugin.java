package fr.nova.plugins.api;

import javafx.stage.Stage;

public abstract class ControllerPlugin {
    private final Stage stage;

    public ControllerPlugin(Stage stage) {
        this.stage = stage;
    }
}
