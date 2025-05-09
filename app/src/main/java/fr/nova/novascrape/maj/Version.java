package fr.nova.novascrape.maj;

import fr.nova.novascrape.db.dao.base.SupermarketDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;

public class Version {
    private static final Logger log = LogManager.getLogger(Version.class);

    public static String getCurrentVersion() {
        try (InputStream is = Version.class.getResourceAsStream("/version.txt");
             BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            return reader.readLine().trim();
        } catch (Exception e) {
            e.printStackTrace();
            return "0.0.0";
        }
    }

    public static void updateLocalVersion(String newVersion) {
        try {
            Files.writeString(Path.of("app/src/main/resources/version.txt"), newVersion);
            log.info("🔁 Fichier version.txt mis à jour.");
        } catch (IOException e) {
            System.err.println("❌ Erreur mise à jour version.txt : " + e.getMessage());
        }
    }

}


