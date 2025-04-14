package fr.nova.novascrape.view;

import fr.nova.novascrape.model.details.RestaurantDetail;
import io.github.palexdev.materialfx.dialogs.MFXGenericDialog;
import io.github.palexdev.materialfx.dialogs.MFXStageDialog;
import javafx.scene.layout.HBox;

import java.util.List;

public class RestaurantDetailView extends DetailView<RestaurantDetail> {
    public RestaurantDetailView(MFXGenericDialog content, MFXStageDialog dialog) {
        super(content, dialog);
    }

    @Override
    protected List<HBox> createLines(RestaurantDetail detail) {
        return List.of(createLine("Nom", detail.getNom()),
                createLine("Adresse", detail.getAdresse()),
                createLine("Téléphone", detail.getTelephone()),
                createLine("Fermeture", detail.getFermetureHebdo()),
                createLine("Métro", detail.getMetro()),
                createLine("Type", detail.getGenreEtablissement()),
                createLine("Services", detail.getServices()),
                createLine("Prix Menu", detail.getPrixMenu()),
                createLine("Cuisine", detail.getTypeCuisine()),
                createLine("Guide", detail.getGuide()));
    }
}
