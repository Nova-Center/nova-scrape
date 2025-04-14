package fr.nova.novascrape.view;

import fr.nova.novascrape.model.details.SupermarketDetail;
import io.github.palexdev.materialfx.dialogs.MFXGenericDialog;
import io.github.palexdev.materialfx.dialogs.MFXStageDialog;
import javafx.scene.layout.HBox;

import java.util.List;

public class SupermarketDetailView extends DetailView<SupermarketDetail> {
    public SupermarketDetailView(MFXGenericDialog content, MFXStageDialog dialog) {
        super(content, dialog);
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

