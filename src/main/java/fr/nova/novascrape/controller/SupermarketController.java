package fr.nova.novascrape.controller;

import fr.nova.novascrape.interfaces.DarkTheme;
import fr.nova.novascrape.model.base.Supermarket;
import fr.nova.novascrape.model.details.SupermarketDetails;
import fr.nova.novascrape.service.WebScrapingDetail;
import fr.nova.novascrape.service.WebScrapingService;
import fr.nova.novascrape.themes.DarkMode;
import fr.nova.novascrape.view.SupermarketCardView;
import fr.nova.novascrape.view.SupermarketDialogView;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.dialogs.MFXGenericDialog;
import io.github.palexdev.materialfx.dialogs.MFXGenericDialogBuilder;
import io.github.palexdev.materialfx.dialogs.MFXStageDialog;
import io.github.palexdev.materialfx.enums.ScrimPriority;
import javafx.application.Platform;
import javafx.concurrent.Task;
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
import java.util.Objects;
import java.util.ResourceBundle;

public class SupermarketController implements Initializable, DarkTheme {
    private final WebScrapingService webScrapingService;
    private final WebScrapingDetail webscrapingDetail;
    private MFXGenericDialog dialogContent;
    private MFXStageDialog dialog;

    @FXML
    private FlowPane cardContainer;

    public SupermarketController(Stage stage) {
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

            dialogContent.setMaxSize(400, 200);
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        displaySupermarket();
    }

    @FXML
    private void openInfo(ActionEvent event, Supermarket supermarket) {
        applyDarkTheme();

        SupermarketDialogView view = new SupermarketDialogView(dialogContent, dialog);
        view.showLoading(supermarket.getNom());

        Task<SupermarketDetails> loadTask = new Task<>() {
            @Override
            protected SupermarketDetails call() {
                return webscrapingDetail.getSupermarketInfo(supermarket);
            }
        };

        loadTask.setOnSucceeded(e -> {
            SupermarketDetails details = loadTask.getValue();
            view.showDetails(supermarket, details);
        });

        loadTask.setOnFailed(e -> {
            view.showError("Erreur", "Impossible de charger les informations.");
            loadTask.getException().printStackTrace();
        });

        new Thread(loadTask).start();
    }

    private void displaySupermarket() {
        List<Supermarket> supermarkets = webScrapingService.getSupermarkets();

        for (Supermarket supermarket : supermarkets) {
            SupermarketCardView cardView = new SupermarketCardView(supermarket);
            VBox card = cardView.getCard();
            MFXButton button = (MFXButton) card.lookup(".card-button");
            button.setOnAction(event -> openInfo(event, supermarket));

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
