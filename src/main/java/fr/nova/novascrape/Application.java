package fr.nova.novascrape;

import fr.brouillard.oss.cssfx.CSSFX;
import io.github.palexdev.materialfx.theming.JavaFXThemes;
import io.github.palexdev.materialfx.theming.MaterialFXStylesheets;
import io.github.palexdev.materialfx.theming.UserAgentBuilder;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;

public class Application extends javafx.application.Application {
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

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/app-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

		stage.initStyle(StageStyle.DECORATED);
        stage.setMaximized(true);
        stage.getIcons().add(new Image(Objects.requireNonNull(NovaResourcesLoader.loadURL("images/nova-scrape-logo.png").toExternalForm())));
        stage.setTitle("Nova Scrape");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
