package fr.nova.novascrape.view;

import fr.nova.novascrape.NovaScrapeUtils;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class LogoContainer {
    private final StackPane logoContainer;
    private final String logoPath;

    public LogoContainer(StackPane logoContainer, String logoPath) {
        this.logoContainer = logoContainer;
        this.logoPath = logoPath;
    }

    public void setLogoContainer() {
        Image image = new Image(NovaScrapeUtils.loadURL(this.logoPath).toExternalForm());
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(100);
        imageView.setFitHeight(100);
        imageView.setPreserveRatio(true);
        this.logoContainer.getChildren().add(imageView);
    }
}
