package fr.nova.novascrape.view;

import fr.nova.novascrape.interfaces.DarkTheme;
import fr.nova.novascrape.model.base.BaseEntity;
import fr.nova.novascrape.model.details.BaseEntityDetail;
import fr.nova.novascrape.themes.DarkMode;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXScrollPane;
import io.github.palexdev.materialfx.dialogs.MFXGenericDialog;
import io.github.palexdev.materialfx.dialogs.MFXStageDialog;
import io.github.palexdev.materialfx.enums.ButtonType;
import io.github.palexdev.mfxresources.fonts.MFXFontIcon;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.List;

import static fr.nova.novascrape.NovaScrapeUtils.createSpacer;

public abstract class BaseEntityView<T extends BaseEntity, D extends BaseEntityDetail> implements DarkTheme {
    protected final MFXGenericDialog dialogContent;
    protected final MFXStageDialog dialog;
    protected final VBox card;
    protected final T entity;

    public BaseEntityView(MFXGenericDialog content, MFXStageDialog dialog, T entity) {
        this.dialogContent = content;
        this.dialog = dialog;
        this.card = new VBox();
        this.entity = entity;
        convertDialogTo("mfx-info-dialog");
    }

    public VBox getCard() {
        card.setStyle("-fx-position-shape: flex;");
        card.setPrefSize(200, 150);

        Label title = new Label(entity.getNom());
        title.getStyleClass().add("card-title");
        title.setWrapText(true);
        title.setMaxWidth(Double.MAX_VALUE);
        title.setAlignment(Pos.CENTER);

        Label address = new Label(entity.getAddress());
        address.setWrapText(true);
        address.getStyleClass().add("card-address");

        card.setSpacing(0);

        MFXButton button = new MFXButton("Détails");
        button.setMinHeight(27);
        button.setMinWidth(70);
        button.setButtonType(ButtonType.RAISED);
        button.getStyleClass().add("card-button");
        button.setPrefWidth(Double.MAX_VALUE);

        card.getChildren().addAll(title, createSpacer(), address, createSpacer(), button);
        card.getStyleClass().add("card");
        return card;
    }

    public MFXButton getCardButton() {
        return (MFXButton) card.lookup(".card-button");
    }

    protected abstract List<HBox> createLines(D detail);

    public void showLoading(String title) {
        dialogContent.setHeaderText(title);
        dialogContent.setHeaderIcon(new MFXFontIcon("fas-circle-info", 18));
        VBox loader = new VBox(new ProgressIndicator());
        loader.setAlignment(Pos.CENTER);
        loader.setPadding(new Insets(20));
        dialogContent.setContent(loader);
        dialog.showDialog();
    }

    public void showDetails(D detail) {
        VBox content = new VBox(10);
        content.setPadding(new Insets(10));

        content.getChildren().addAll(
                createLines(detail)
        );

        MFXScrollPane scrollPane = new MFXScrollPane(content);
        scrollPane.setFitToWidth(true);
        scrollPane.setPrefViewportHeight(250);
        scrollPane.setStyle("-fx-background-color: transparent;");

        dialogContent.setContent(scrollPane);
    }

    private void convertDialogTo(String styleClass) {
        if (dialogContent == null) return;

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

    public HBox createLine(String titre, String valeur) {
        Label labelTitre = new Label(titre + " : ");
        labelTitre.setStyle("-fx-font-weight: bold; -fx-text-fill: #333;");

        Label labelValeur = new Label(valeur);
        labelValeur.setStyle("-fx-text-fill: #555;");

        return new HBox(5, labelTitre, labelValeur);
    }

    @Override
    public void applyDarkTheme() {
        if (DarkMode.getInstance().isDarkModeEnabled() && !dialogContent.getStyleClass().contains("mfx-dialog-custom")) {
            dialogContent.getStyleClass().add("mfx-dialog-custom");
        }
        if (!DarkMode.getInstance().isDarkModeEnabled()) {
            dialogContent.getStyleClass().remove("mfx-dialog-custom");
        }
    }
}
