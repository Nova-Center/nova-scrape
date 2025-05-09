package fr.nova.novascrape.maj;

import java.io.File;
import java.io.IOException;

public class UpdaterExecutor {

    public static void launchNewJar(String jarPath) {
        try {
            ProcessBuilder builder = new ProcessBuilder("java", "-jar", jarPath);
            builder.directory(new File("."));
            builder.start();
            System.out.println("🚀 Nouvelle version lancée !");
            System.exit(0); // On quitte l’ancienne version
        } catch (IOException e) {
            System.err.println("❌ Impossible de lancer la nouvelle version : " + e.getMessage());
        }
    }
}

