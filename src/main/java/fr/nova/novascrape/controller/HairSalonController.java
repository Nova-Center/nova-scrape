package fr.nova.novascrape.controller;

import fr.nova.novascrape.model.base.HairSalon;
import fr.nova.novascrape.model.detail.SalonDetail;
import fr.nova.novascrape.service.WebScrapingDetail;
import fr.nova.novascrape.service.WebScrapingService;
import fr.nova.novascrape.view.HairSalonView;
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
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class HairSalonController implements Initializable {
    private WebScrapingService webScrapingService;
    private WebScrapingDetail webscrapingDetail;
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

            dialogContent.setMaxSize(600, 500);
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        displayHairSalon();
    }

    @FXML
    private void openInfo(ActionEvent event, HairSalon hairSalon) {
        MFXFontIcon infoIcon = new MFXFontIcon("fas-circle-info", 18);
        dialogContent.setHeaderIcon(infoIcon);
        dialogContent.setHeaderText(hairSalon.getNom());

        // TODO: Add the details of supermarket
        String url = hairSalon.getLienDetail();
        SalonDetail salonDetail = webscrapingDetail.recupererDetailSalon(url);

        VBox contentBox = new VBox(10);
        contentBox.setPadding(new Insets(10));

        contentBox.getChildren().addAll(
            createLigne("Nom", salonDetail.getNom()),
            createLigne("Adresse", salonDetail.getAdresse()),
            createLigne("Horaire", salonDetail.getHoraire()),
            createLigne("Tarif", salonDetail.getTarif())
        );


        ScrollPane scrollPane = new ScrollPane(contentBox);
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

    private void convertDialogTo(String styleClass) {
        dialogContent.getStyleClass().removeIf(
                s -> s.equals("mfx-info-dialog") || s.equals("mfx-warn-dialog") || s.equals("mfx-error-dialog")
        );

        if (styleClass != null)
            dialogContent.getStyleClass().add(styleClass);
    }
}
