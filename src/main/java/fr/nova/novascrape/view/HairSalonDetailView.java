package fr.nova.novascrape.view;

import fr.nova.novascrape.model.details.HairSalonDetail;
import io.github.palexdev.materialfx.dialogs.MFXGenericDialog;
import io.github.palexdev.materialfx.dialogs.MFXStageDialog;
import javafx.scene.layout.HBox;

import java.util.List;

public class HairSalonDetailView extends DetailView<HairSalonDetail> {
    public HairSalonDetailView(MFXGenericDialog content, MFXStageDialog dialog) {
        super(content, dialog);
    }

    @Override
    protected List<HBox> createLines(HairSalonDetail detail) {
        return List.of(
                createLine("Nom", detail.getNom()),
                createLine("Adresse", detail.getAdresse()),
                createLine("Horaire", detail.getHoraire()),
                createLine("Tarif", detail.getTarif())
        );
    }
}
