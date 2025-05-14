package fr.nova.novascrape.maj;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;

public class UpdaterExecutor {
    private static final Logger log = LogManager.getLogger(UpdaterExecutor.class);

    public static void launchNewJar(String jarPath) {
        try {
            ProcessBuilder builder = new ProcessBuilder("java", "-jar", jarPath);
            builder.directory(new File("."));
            builder.start();
            log.info("🚀 Nouvelle version lancée !");
            System.exit(0); // On quitte l’ancienne version
        } catch (IOException e) {
            System.err.println("❌ Impossible de lancer la nouvelle version : " + e.getMessage());
        }
    }
}

