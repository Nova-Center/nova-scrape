package fr.nova.novascrape.view;

import fr.nova.novascrape.model.base.Supermarket;
import fr.nova.novascrape.model.detail.SupermarketDetails;
import io.github.palexdev.materialfx.controls.MFXScrollPane;
import io.github.palexdev.materialfx.dialogs.MFXGenericDialog;
import io.github.palexdev.materialfx.dialogs.MFXStageDialog;
import io.github.palexdev.mfxcore.controls.Label;
import io.github.palexdev.mfxresources.fonts.MFXFontIcon;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.VBox;

public class SupermarketDialogView {

    private final MFXGenericDialog dialogContent;
    private final MFXStageDialog dialog;

    public SupermarketDialogView(MFXGenericDialog dialogContent, MFXStageDialog dialog) {
        this.dialogContent = dialogContent;
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

    public void showDetails(Supermarket supermarket, SupermarketDetails details) {
        VBox contentBox = new VBox(10);
        contentBox.setPadding(new Insets(10));

        contentBox.getChildren().addAll(
                createLine("Nom", supermarket.getNom()),
                createLine("Adresse", supermarket.getAdresse()),
                createLine("Tel", details.getTelephone()),
                createLine("Horaires", details.getHoraires())
        );

        MFXScrollPane scrollPane = new MFXScrollPane(contentBox);
        scrollPane.setFitToWidth(true);
        scrollPane.setPrefViewportHeight(200);
        scrollPane.setStyle("-fx-background-color: transparent;");
        scrollPane.getStyleClass().add("dialog-details");

        dialogContent.setContent(scrollPane);
    }

    private void convertDialogTo(String styleClass) {
        dialogContent.getStyleClass().removeIf(
                s -> s.equals("mfx-info-dialog") || s.equals("mfx-warn-dialog") || s.equals("mfx-error-dialog")
        );

        if (styleClass != null)
            dialogContent.getStyleClass().add(styleClass);
    }

    public void showError(String title, String message) {
        VBox errorBox = new VBox(new Label(message));
        errorBox.setPadding(new Insets(10));
        dialogContent.setHeaderText(title);
        dialogContent.setContent(errorBox);
    }

    private Node createLine(String label, String value) {
        return new Label(label + ": " + value);
    }
}

