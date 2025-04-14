package fr.nova.novascrape;

import fr.brouillard.oss.cssfx.CSSFX;
import fr.nova.novascrape.controller.ApplicationController;
import io.github.palexdev.materialfx.theming.JavaFXThemes;
import io.github.palexdev.materialfx.theming.MaterialFXStylesheets;
import io.github.palexdev.materialfx.theming.UserAgentBuilder;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Objects;

public class Application extends javafx.application.Application {
    private static final Logger log = LogManager.getLogger(Application.class);

    @Override
    public void start(Stage stage) throws IOException {
        CSSFX.start();

        UserAgentBuilder.builder()
                .themes(JavaFXThemes.MODENA)
                .themes(MaterialFXStylesheets.forAssemble(true))
                .setDeploy(true)
                .setResolveAssets(true)
                .build()
                .setGlobal();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/Application.fxml"));
        fxmlLoader.setControllerFactory(__ -> new ApplicationController(stage));
        Scene scene = new Scene(fxmlLoader.load());

        stage.initStyle(StageStyle.DECORATED);
        stage.setMaximized(true);
        stage.getIcons().add(new Image(Objects.requireNonNull(NovaScrapeUtils.loadURL("images/nova-scrape-logo.png").toExternalForm())));
        stage.setTitle("Nova Scrape");
        stage.setScene(scene);
        stage.show();

        log.info("Démarrage de Nova Scrape");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
