package fr.nova.novascrape.controller;

import fr.nova.novascrape.model.base.Supermarket;
import fr.nova.novascrape.model.details.SupermarketDetail;
import fr.nova.novascrape.view.BaseEntityView;
import fr.nova.novascrape.view.SupermarketView;
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
    protected Function<Supermarket, BaseEntityView<Supermarket, SupermarketDetail>> getCardFactory() {
        return supermarket -> new SupermarketView(dialogContent, dialog, supermarket);
    }

    @Override
    protected BaseEntityView<Supermarket, SupermarketDetail> createDetailView(Supermarket entity) {
        return new SupermarketView(dialogContent, dialog, entity);
    }

    @Override
    protected SupermarketDetail fetchDetails(Supermarket entity) {
        return webscrapingDetail.getSupermarketDetail(entity);
    }
}
