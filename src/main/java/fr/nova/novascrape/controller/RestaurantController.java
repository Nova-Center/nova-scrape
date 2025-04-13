package fr.nova.novascrape.controller;

import fr.nova.novascrape.interfaces.DarkTheme;
import fr.nova.novascrape.model.base.HairSalon;
import fr.nova.novascrape.model.base.Restaurant;
import fr.nova.novascrape.model.detail.RestaurantDetail;
import fr.nova.novascrape.service.WebScrapingDetail;
import fr.nova.novascrape.service.WebScrapingService;
import fr.nova.novascrape.themes.DarkMode;
import fr.nova.novascrape.view.HairSalonView;
import fr.nova.novascrape.view.RestaurantDetailView;
import fr.nova.novascrape.view.RestaurantView;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.dialogs.MFXGenericDialog;
import io.github.palexdev.materialfx.dialogs.MFXGenericDialogBuilder;
import io.github.palexdev.materialfx.dialogs.MFXStageDialog;
import io.github.palexdev.materialfx.enums.ScrimPriority;
import io.github.palexdev.mfxresources.fonts.MFXFontIcon;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.Label;


import java.awt.*;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.ResourceBundle;

public class RestaurantController implements Initializable, DarkTheme {
    private final WebScrapingService webScrapingService;
    private final WebScrapingDetail webscrapingDetail;
    private MFXGenericDialog dialogContent;
    private MFXStageDialog dialog;

    @FXML
    private FlowPane cardContainer;

    public RestaurantController(Stage stage) {
        this.webScrapingService = new WebScrapingService();
        this.webscrapingDetail = new WebScrapingDetail();

        Platform.runLater(() -> {
            this.dialogContent = MFXGenericDialogBuilder.build()
                    .setContentText("Loading...")
                    .makeScrollable(true)
                    .get();
            this.dialog = MFXGenericDialogBuilder.build(dialogContent)
                    .toStageDialogBuilder()
                    .initOwner(stage)
                    .initModality(Modality.APPLICATION_MODAL)
                    .setDraggable(true)
                    .setTitle("Dialogs Preview")
                    .setOwnerNode(cardContainer)
                    .setScrimPriority(ScrimPriority.WINDOW)
                    .setScrimOwner(true)
                    .get();

            dialogContent.addActions(
                    Map.entry(new MFXButton("Fermer"), event -> dialog.close())
            );

            dialogContent.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/fr/nova/novascrape/css/themes/Dark.css")).toExternalForm());

            dialogContent.setMaxSize(600, 500);

        });
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        displayRestaurants();
    }

    @FXML
    private void openInfo(ActionEvent event, Restaurant restaurant) {
        applyDarkTheme();
        RestaurantDetailView view = new RestaurantDetailView(dialogContent, dialog);
        view.showLoading(restaurant.getNom());

        Task<RestaurantDetail> loadTask = new Task<>() {
            @Override
            protected RestaurantDetail call() {
                return webscrapingDetail.recupererDetailRestaurant(restaurant.getLienDetail());
            }
        };

        loadTask.setOnSucceeded(e -> {
            RestaurantDetail details = loadTask.getValue();
            view.showDetails(restaurant, details);
        });

        loadTask.setOnFailed(e -> {
            view.showError("Impossible de charger les informations.");
            loadTask.getException().printStackTrace();
        });

        new Thread(loadTask).start();
    }


    private HBox createLigne(String titre, String valeur) {
        Label labelTitre = new Label(titre + " : ");
        labelTitre.setStyle("-fx-font-weight: bold; -fx-text-fill: #333;");

        Label labelValeur = new Label(valeur);
        labelValeur.setStyle("-fx-text-fill: #555;");

        HBox ligne = new HBox(5, labelTitre, labelValeur); // 5 = espace entre les deux
        return ligne;
    }


    private void displayRestaurants() {
        List<Restaurant> restaurants = webScrapingService.getRestaurants();

        for (Restaurant restaurant : restaurants) {
            RestaurantView cardView = new RestaurantView(restaurant);
            VBox card = cardView.getCard();
            MFXButton button = (MFXButton) card.lookup(".card-button");
            button.setOnAction(event -> openInfo(event, restaurant));

            cardContainer.getChildren().add(card);
        }
    }

    @Override
    public void applyDarkTheme() {
        if (DarkMode.getInstance().isDarkModeEnabled() && !dialogContent.getStyleClass().contains("mfx-dialog-custom")) {
            dialogContent.getStyleClass().add("mfx-dialog-custom");
        } else {
            dialogContent.getStyleClass().remove("mfx-dialog-custom");
        }
    }
}
