package fr.nova.novascrape.view;

import fr.nova.novascrape.model.base.Supermarket;
import fr.nova.novascrape.model.details.SupermarketDetail;
import io.github.palexdev.materialfx.dialogs.MFXGenericDialog;
import io.github.palexdev.materialfx.dialogs.MFXStageDialog;
import javafx.scene.layout.HBox;

import java.util.List;

public class SupermarketView extends BaseEntityView<Supermarket, SupermarketDetail> {
    public SupermarketView(MFXGenericDialog content, MFXStageDialog dialog, Supermarket entity) {
        super(content, dialog, entity);
    }

    @Override
    protected List<HBox> createLines(SupermarketDetail detail) {
        return List.of(createLine("Nom", detail.getNom()),
                createLine("Adresse", detail.getAdresse()),
                createLine("Tel", detail.getTelephone()),
                createLine("Horaires", detail.getHoraires())
        );
    }
}

