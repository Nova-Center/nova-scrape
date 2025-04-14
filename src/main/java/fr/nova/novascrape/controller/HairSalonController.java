package fr.nova.novascrape.controller;

import fr.nova.novascrape.model.base.HairSalon;
import fr.nova.novascrape.model.details.HairSalonDetail;
import fr.nova.novascrape.view.BaseEntityView;
import fr.nova.novascrape.view.HairSalonView;
import javafx.stage.Stage;

import java.util.List;
import java.util.function.Function;

public class HairSalonController extends BaseController<HairSalon, HairSalonDetail> {
    public HairSalonController(Stage stage) {
        super(stage);
    }

    @Override
    protected List<HairSalon> getItems() {
        return webScrapingService.getHairSalons();
    }

    @Override
    protected Function<HairSalon, BaseEntityView<HairSalon, HairSalonDetail>> getCardFactory() {
        return hairSalon -> new HairSalonView(dialogContent, dialog, hairSalon);
    }

    @Override
    protected BaseEntityView<HairSalon, HairSalonDetail> createDetailView(HairSalon entity) {
        return new HairSalonView(dialogContent, dialog, entity);
    }

    @Override
    protected HairSalonDetail fetchDetails(HairSalon salon) {
        return webscrapingDetail.getHairSalonDetail(salon.getLienDetail());
    }
}
