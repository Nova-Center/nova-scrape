package fr.nova.example.plugin;

import fr.nova.plugins.api.ControllerPlugin;
import fr.nova.plugins.api.NovaScrapePlugin;
import javafx.stage.Stage;
import org.pf4j.Extension;
import org.pf4j.Plugin;
import org.pf4j.PluginWrapper;

import java.net.URL;

public class ExamplePlugin extends Plugin {
    public ExamplePlugin(PluginWrapper wrapper) {
        super(wrapper);
    }

    @Extension
    public static class Test implements NovaScrapePlugin {
        @Override
        public String getName() {
            return "Example Plugin Extension";
        }

        @Override
        public String getViewName() {
            return "EXAMPLE PLUGIN";
        }

        @Override
        public URL getFxmlPath() {
            return ExamplePlugin.class.getResource("/fxml/Example.fxml");
        }

        @Override
        public String getIcon() {
            return "fas-circle-info";
        }

        @Override
        public ControllerPlugin createController(Stage stage) {
            return new Controller(stage);
        }
    }
}
