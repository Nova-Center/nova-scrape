package fr.nova.novascrape.controller;

import fr.nova.novascrape.interfaces.DarkTheme;
import fr.nova.novascrape.model.base.HairSalon;
import fr.nova.novascrape.model.detail.HairSalonDetails;
import fr.nova.novascrape.service.WebScrapingDetail;
import fr.nova.novascrape.service.WebScrapingService;
import fr.nova.novascrape.themes.DarkMode;
import fr.nova.novascrape.view.HairSalonDetailView;
import fr.nova.novascrape.view.HairSalonView;
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

public class HairSalonController implements Initializable, DarkTheme {
    private final WebScrapingService webScrapingService;
    private final WebScrapingDetail webscrapingDetail;
    private MFXGenericDialog dialogContent;
    private MFXStageDialog dialog;

    @FXML
    private FlowPane cardContainer;

    public HairSalonController(Stage stage) {
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
        displayHairSalon();
    }

    @FXML
    private void openInfo(ActionEvent event, HairSalon hairSalon) {
        applyDarkTheme();
        HairSalonDetailView view = new HairSalonDetailView(dialogContent, dialog);
        view.showLoading(hairSalon.getNom());

        applyDarkTheme();

        Task<HairSalonDetails> task = new Task<>() {
            @Override
            protected HairSalonDetails call() {
                return webscrapingDetail.recupererDetailSalon(hairSalon.getLienDetail());
            }
        };

        task.setOnSucceeded(e -> view.showDetails(task.getValue()));
        task.setOnFailed(e -> {
            view.showError("Impossible de charger les informations.");
            task.getException().printStackTrace();
        });

        new Thread(task).start();
    }

    private void displayHairSalon() {
        List<HairSalon> hairSalons = webScrapingService.getHairSalons();

        for (HairSalon hairSalon : hairSalons) {
            HairSalonView cardView = new HairSalonView(hairSalon);
            VBox card = cardView.getCard();
            MFXButton button = (MFXButton) card.lookup(".card-button");
            button.setOnAction(event -> openInfo(event, hairSalon));

            cardContainer.getChildren().add(card);
        }
    }

    @Override
    public void applyDarkTheme() {
        if (DarkMode.getInstance().isDarkModeEnabled()) {
            dialogContent.getStyleClass().add("mfx-dialog-custom");
        } else {
            dialogContent.getStyleClass().remove("mfx-dialog-custom");
        }
    }
}
