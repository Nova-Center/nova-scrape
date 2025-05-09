package fr.nova.novascrape;

import fr.brouillard.oss.cssfx.CSSFX;
import fr.nova.novascrape.controller.ApplicationController;
import fr.nova.novascrape.db.Database;
import fr.nova.novascrape.maj.*;
import fr.nova.plugins.api.NovaScrapePlugin;
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
import org.pf4j.DefaultPluginManager;
import org.pf4j.PluginManager;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Application extends javafx.application.Application {
    private static final Logger log = LogManager.getLogger(Application.class);

    private List<NovaScrapePlugin> loadPlugins() {
        log.info("🔄 Loading plugins...");

        String projectRoot = System.getProperty("user.dir");
        Path pluginsDir = Paths.get(projectRoot, "plugins");
        PluginManager pluginManager = new DefaultPluginManager(pluginsDir);

        pluginManager.loadPlugins();
        pluginManager.startPlugins();
        log.info("🔌 Plugins loaded : {}", pluginManager.getStartedPlugins());

        List<NovaScrapePlugin> plugins = pluginManager.getExtensions(NovaScrapePlugin.class);
        if (plugins.isEmpty()) {
            log.info("❌ No plugins found !");
            return Collections.emptyList();
        }
        for (NovaScrapePlugin plugin : plugins) {
            log.info("❇️ Plugin ready to use : {}", plugin.getName());
        }

        return plugins;
    }

    @Override
    public void start(Stage stage) throws IOException {
        ReleaseInfo latest = UpdateChecker.getLatestReleaseInfo();
        String localVersion = Version.getCurrentVersion();

        if (latest != null && !localVersion.equals(latest.getVersion())) {
            log.info("🚀 Update available : {}", latest.getVersion());
            // 1. Télécharger le nouveau JAR
            String newJarPath = "nova-scrape-latest.jar";
            UpdateDownloader.downloadNewVersion(latest.getJarUrl(), newJarPath);
            // 2. Mettre à jour le fichier de version locale
            Version.updateLocalVersion(latest.getVersion());
            // 3. Lancer le nouveau JAR
            UpdaterExecutor.launchNewJar(newJarPath);
            return; // stop le reste du programme
        } else {
            log.info("✅ Software updated !");
        }

        List<NovaScrapePlugin> plugins = loadPlugins();

        CSSFX.start();
        Database.initDatabase();
        UserAgentBuilder.builder()
                .themes(JavaFXThemes.MODENA)
                .themes(MaterialFXStylesheets.forAssemble(true))
                .setDeploy(true)
                .setResolveAssets(true)
                .build()
                .setGlobal();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/Application.fxml"));
        fxmlLoader.setControllerFactory(__ -> new ApplicationController(stage, plugins));
        Scene scene = new Scene(fxmlLoader.load());

        stage.initStyle(StageStyle.DECORATED);
        stage.setMaximized(true);
        stage.getIcons().add(new Image(Objects.requireNonNull(NovaScrapeUtils.loadURL("images/nova-scrape-logo.png").toExternalForm())));
        stage.setTitle("Nova Scrape");
        stage.setScene(scene);
        stage.show();

        log.info("Launch of Nova Scrape");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
