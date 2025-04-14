package fr.nova.novascrape.view;

import fr.nova.novascrape.model.base.Supermarket;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.enums.ButtonType;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import static fr.nova.novascrape.NovaScrapeUtils.createSpacer;

public class SupermarketView extends CardView<Supermarket> {
    public SupermarketView(Supermarket entity) {
        super(entity);
    }
}
