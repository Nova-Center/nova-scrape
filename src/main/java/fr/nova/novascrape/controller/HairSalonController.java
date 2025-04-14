package fr.nova.novascrape.controller;

import fr.nova.novascrape.model.base.HairSalon;
import fr.nova.novascrape.model.details.HairSalonDetail;
import fr.nova.novascrape.view.CardView;
import fr.nova.novascrape.view.DetailView;
import fr.nova.novascrape.view.HairSalonDetailView;
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
    protected Function<HairSalon, CardView<HairSalon>> getCardFactory() {
        return HairSalonView::new;
    }

    @Override
    protected DetailView<HairSalonDetail> createDetailView() {
        return new HairSalonDetailView(dialogContent, dialog);
    }

    @Override
    protected HairSalonDetail fetchDetails(HairSalon salon) {
        return webscrapingDetail.getHairSalonDetail(salon.getLienDetail());
    }
}
