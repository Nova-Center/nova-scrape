package fr.nova.novascrape.controller;

import fr.nova.novascrape.model.base.Supermarket;
import fr.nova.novascrape.service.WebScrapingService;
import fr.nova.novascrape.view.SupermarketCardView;
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

public class SupermarketController implements Initializable {
    private WebScrapingService webScrapingService;

    private MFXGenericDialog dialogContent;
    private MFXStageDialog dialog;

    @FXML
    private FlowPane cardContainer;

    public SupermarketController(Stage stage) {
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
        displaySupermarket();
    }

    @FXML
    private void openInfo(ActionEvent event, Supermarket supermarket) {
        MFXFontIcon infoIcon = new MFXFontIcon("fas-circle-info", 18);
        dialogContent.setHeaderIcon(infoIcon);
        dialogContent.setHeaderText(supermarket.getNom());

        // TODO: Add the details of supermarket
        dialogContent.setContentText(supermarket.getAdresse());
        convertDialogTo("mfx-info-dialog");
        dialog.showDialog();
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

    private void convertDialogTo(String styleClass) {
        dialogContent.getStyleClass().removeIf(
                s -> s.equals("mfx-info-dialog") || s.equals("mfx-warn-dialog") || s.equals("mfx-error-dialog")
        );

        if (styleClass != null)
            dialogContent.getStyleClass().add(styleClass);
    }
}
