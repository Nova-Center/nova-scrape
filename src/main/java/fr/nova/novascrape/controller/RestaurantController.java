package fr.nova.novascrape.controller;

import fr.nova.novascrape.model.base.HairSalon;
import fr.nova.novascrape.model.base.Restaurant;
import fr.nova.novascrape.model.detail.RestaurantDetail;
import fr.nova.novascrape.service.WebScrapingDetail;
import fr.nova.novascrape.service.WebScrapingService;
import fr.nova.novascrape.view.HairSalonView;
import fr.nova.novascrape.view.RestaurantView;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.dialogs.MFXGenericDialog;
import io.github.palexdev.materialfx.dialogs.MFXGenericDialogBuilder;
import io.github.palexdev.materialfx.dialogs.MFXStageDialog;
import io.github.palexdev.materialfx.enums.ScrimPriority;
import io.github.palexdev.mfxresources.fonts.MFXFontIcon;
import javafx.application.Platform;
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
import java.util.ResourceBundle;

public class RestaurantController implements Initializable {
    private WebScrapingService webScrapingService;
    private WebScrapingDetail webscrapingDetail;
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

            dialogContent.setMaxSize(600, 500);

        });
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        displayRestaurants();
    }

    @FXML
    private void openInfo(ActionEvent event, Restaurant restaurant) {
        MFXFontIcon infoIcon = new MFXFontIcon("fas-circle-info", 18);
        dialogContent.setHeaderIcon(infoIcon);
        dialogContent.setHeaderText(restaurant.getNom());

        // TODO: Add the details of restaurants
        String url = restaurant.getLienDetail();
        RestaurantDetail restaurantDetails = webscrapingDetail.recupererDetailRestaurant(url);

        VBox content = new VBox(10); //  espace entre les lignes
        content.setStyle("-fx-padding: 10;"); // marge interieur


        // Ajouter les lignes avec titres + contenu
        content.getChildren().add(createLigne("Nom", restaurantDetails.getNom()));
        content.getChildren().add(createLigne("Adresse", restaurantDetails.getAdresse()));
        content.getChildren().add(createLigne("Téléphone", restaurantDetails.getTelephone()));
        content.getChildren().add(createLigne("Fermeture", restaurantDetails.getFermetureHebdo()));
        content.getChildren().add(createLigne("Métro", restaurantDetails.getMetro()));
        content.getChildren().add(createLigne("Type", restaurantDetails.getGenreEtablissement()));
        content.getChildren().add(createLigne("Services", restaurantDetails.getServices()));
        content.getChildren().add(createLigne("Prix Menu", restaurantDetails.getPrixMenu()));
        content.getChildren().add(createLigne("Cuisine", restaurantDetails.getTypeCuisine()));
        content.getChildren().add(createLigne("Guide", restaurantDetails.getGuide()));

        javafx.scene.control.ScrollPane scrollPane = new ScrollPane(content);
        scrollPane.setFitToWidth(true); // pour que ça ne dépasse pas en largeur
        scrollPane.setPrefViewportHeight(200); // hauteur visible avant scroll
        scrollPane.setStyle("-fx-background-color: transparent;");

        dialogContent.setContent(scrollPane); // on remplace setContentText par setContent
        convertDialogTo("mfx-info-dialog");
        dialog.showDialog();
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

    private void convertDialogTo(String styleClass) {
        dialogContent.getStyleClass().removeIf(
                s -> s.equals("mfx-info-dialog") || s.equals("mfx-warn-dialog") || s.equals("mfx-error-dialog")
        );

        if (styleClass != null)
            dialogContent.getStyleClass().add(styleClass);
    }
}
