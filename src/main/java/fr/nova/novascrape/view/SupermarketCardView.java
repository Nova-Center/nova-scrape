package fr.nova.novascrape.view;

import fr.nova.novascrape.model.base.Supermarket;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.enums.ButtonType;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class SupermarketCardView {
    private final Supermarket item;

    public SupermarketCardView(Supermarket item) {
        this.item = item;
    }

    public VBox getCard() {
        VBox card = new VBox();

        card.setStyle("-fx-position-shape: flex;");
        card.setPrefSize(200, 150);

        Label title = new Label(item.getNom());
        title.getStyleClass().add("card-title");
        title.setWrapText(true);
        title.setMaxWidth(Double.MAX_VALUE);
        title.setAlignment(Pos.CENTER);

        Label address = new Label(item.getAdresse());
        address.setWrapText(true);
        address.getStyleClass().add("card-address");

        card.setSpacing(0);

        MFXButton button = new MFXButton("Détails");
        button.setMinHeight(27);
        button.setMinWidth(70);
        button.setButtonType(ButtonType.RAISED);
        button.getStyleClass().add("card-button");
        button.setPrefWidth(Double.MAX_VALUE);
        button.setOnAction(event -> {
            System.out.println("Button clicked for " + item.getNom());
        });
        card.getChildren().addAll(title, createSpacer(), address, createSpacer(), button);
        card.getStyleClass().add("card");
        return card;
    }

    private Region createSpacer() {
        Region spacer = new Region();
        VBox.setVgrow(spacer, Priority.ALWAYS);
        return spacer;
    }
}
