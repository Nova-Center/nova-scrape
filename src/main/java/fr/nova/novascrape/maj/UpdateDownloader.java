package fr.nova.novascrape.maj;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;

public class UpdateDownloader {

    public static void downloadNewVersion(String downloadUrl, String outputFilePath) {
        try (BufferedInputStream in = new BufferedInputStream(new URL(downloadUrl).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(outputFilePath)) {

            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
            }

            System.out.println("✅ Nouvelle version téléchargée avec succès !");
        } catch (IOException e) {
            System.err.println("❌ Erreur lors du téléchargement : " + e.getMessage());
        }
    }
}
