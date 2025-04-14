package fr.nova.novascrape.view;

import fr.nova.novascrape.model.base.BaseEntity;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.enums.ButtonType;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import static fr.nova.novascrape.NovaScrapeUtils.createSpacer;

public abstract class CardView<T extends BaseEntity> {
    protected T entity;
    protected VBox card;

    public CardView(T entity) {
        this.entity = entity;
        this.card = new VBox();
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
}

