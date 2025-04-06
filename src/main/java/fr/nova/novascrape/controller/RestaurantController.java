package fr.nova.novascrape.controller;

import fr.nova.novascrape.model.base.HairSalon;
import fr.nova.novascrape.model.base.Restaurant;
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
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class RestaurantController implements Initializable {
    private WebScrapingService webScrapingService;

    private MFXGenericDialog dialogContent;
    private MFXStageDialog dialog;

    @FXML
    private FlowPane cardContainer;

    public RestaurantController(Stage stage) {
        this.webScrapingService = new WebScrapingService();

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

            dialogContent.setMaxSize(400, 200);
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

        // TODO: Add the details of supermarket
        dialogContent.setContentText(restaurant.getAdresse());
        convertDialogTo("mfx-info-dialog");
        dialog.showDialog();
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
