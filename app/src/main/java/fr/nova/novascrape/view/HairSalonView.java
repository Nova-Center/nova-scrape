package fr.nova.novascrape.view;

import fr.nova.novascrape.model.base.HairSalon;
import fr.nova.novascrape.model.details.HairSalonDetail;
import io.github.palexdev.materialfx.dialogs.MFXGenericDialog;
import io.github.palexdev.materialfx.dialogs.MFXStageDialog;
import javafx.scene.layout.HBox;

import java.util.List;

public class HairSalonView extends BaseEntityView<HairSalon, HairSalonDetail> {
    public HairSalonView(MFXGenericDialog content, MFXStageDialog dialog, HairSalon entity) {
        super(content, dialog, entity);
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
