package fr.nova.novascrape.controller;

import fr.nova.novascrape.model.base.Supermarket;
import fr.nova.novascrape.model.details.SupermarketDetail;
import fr.nova.novascrape.view.CardView;
import fr.nova.novascrape.view.DetailView;
import fr.nova.novascrape.view.SupermarketView;
import fr.nova.novascrape.view.SupermarketDetailView;
import javafx.stage.Stage;

import java.util.List;
import java.util.function.Function;

public class SupermarketController extends BaseController<Supermarket, SupermarketDetail> {

    public SupermarketController(Stage stage) {
        super(stage);
    }

    @Override
    protected List<Supermarket> getItems() {
        return webScrapingService.getSupermarkets();
    }

    @Override
    protected Function<Supermarket, CardView<Supermarket>> getCardFactory() {
        return SupermarketView::new;
    }

    @Override
    protected DetailView<SupermarketDetail> createDetailView() {
        return new SupermarketDetailView(dialogContent, dialog);
    }

    @Override
    protected SupermarketDetail fetchDetails(Supermarket entity) {
        return webscrapingDetail.getSupermarketDetail(entity);
    }
}
