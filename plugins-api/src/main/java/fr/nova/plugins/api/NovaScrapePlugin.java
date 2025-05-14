package fr.nova.plugins.api;

import javafx.stage.Stage;
import org.pf4j.ExtensionPoint;

import java.net.URL;

public interface NovaScrapePlugin extends ExtensionPoint {
    String getName();
    String getViewName();
    URL getFxmlPath();
    String getIcon();
    ControllerPlugin createController(Stage stage);
}
