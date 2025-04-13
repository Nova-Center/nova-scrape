package fr.nova.novascrape.view;

import fr.nova.novascrape.model.base.Restaurant;
import fr.nova.novascrape.model.detail.RestaurantDetails;
import io.github.palexdev.materialfx.controls.MFXScrollPane;
import io.github.palexdev.materialfx.dialogs.MFXGenericDialog;
import io.github.palexdev.materialfx.dialogs.MFXStageDialog;
import io.github.palexdev.mfxresources.fonts.MFXFontIcon;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class RestaurantDetailView {
    private final MFXGenericDialog dialogContent;
    private final MFXStageDialog dialog;

    public RestaurantDetailView(MFXGenericDialog content, MFXStageDialog dialog) {
        this.dialogContent = content;
        this.dialog = dialog;
        convertDialogTo("mfx-info-dialog");
    }

    public void showLoading(String title) {
        dialogContent.setHeaderText(title);
        dialogContent.setHeaderIcon(new MFXFontIcon("fas-circle-info", 18));
        VBox loader = new VBox(new ProgressIndicator());
        loader.setAlignment(Pos.CENTER);
        loader.setPadding(new Insets(20));
        dialogContent.setContent(loader);
        dialog.showDialog();
    }

    public void showDetails(Restaurant restaurant, RestaurantDetails detail) {
        VBox content = new VBox(10);
        content.setPadding(new Insets(10));

        content.getChildren().addAll(
                createLine("Nom", detail.getNom()),
                createLine("Adresse", detail.getAdresse()),
                createLine("Téléphone", detail.getTelephone()),
                createLine("Fermeture", detail.getFermetureHebdo()),
                createLine("Métro", detail.getMetro()),
                createLine("Type", detail.getGenreEtablissement()),
                createLine("Services", detail.getServices()),
                createLine("Prix Menu", detail.getPrixMenu()),
                createLine("Cuisine", detail.getTypeCuisine()),
                createLine("Guide", detail.getGuide())
        );

        MFXScrollPane scrollPane = new MFXScrollPane(content);
        scrollPane.setFitToWidth(true);
        scrollPane.setPrefViewportHeight(250);
        scrollPane.setStyle("-fx-background-color: transparent;");

        dialogContent.setContent(scrollPane);
    }

    private void convertDialogTo(String styleClass) {
        dialogContent.getStyleClass().removeIf(
                s -> s.equals("mfx-info-dialog") || s.equals("mfx-warn-dialog") || s.equals("mfx-error-dialog")
        );

        if (styleClass != null)
            dialogContent.getStyleClass().add(styleClass);
    }

    public void showError(String message) {
        VBox errorBox = new VBox(new Label(message));
        errorBox.setPadding(new Insets(10));
        dialogContent.setContent(errorBox);
    }

    private HBox createLine(String titre, String valeur) {
        Label labelTitre = new Label(titre + " : ");
        labelTitre.setStyle("-fx-font-weight: bold; -fx-text-fill: #333;");

        Label labelValeur = new Label(valeur);
        labelValeur.setStyle("-fx-text-fill: #555;");

        return new HBox(5, labelTitre, labelValeur);
    }
}
