package fr.nova.novascrape.controller;

import fr.nova.novascrape.model.base.Restaurant;
import fr.nova.novascrape.model.details.RestaurantDetail;
import fr.nova.novascrape.view.BaseEntityView;
import fr.nova.novascrape.view.RestaurantView;
import javafx.stage.Stage;


import java.util.List;
import java.util.function.Function;

public class RestaurantController extends BaseController<Restaurant, RestaurantDetail> {
    public RestaurantController(Stage stage) {
        super(stage);
    }

    @Override
    protected List<Restaurant> getItems() {
        return webScrapingService.getRestaurants();
    }

    @Override
    protected Function<Restaurant, BaseEntityView<Restaurant, RestaurantDetail>> getCardFactory() {
        return restaurant -> new RestaurantView(dialogContent, dialog, restaurant);
    }

    @Override
    protected BaseEntityView<Restaurant, RestaurantDetail> createDetailView(Restaurant entity) {
        return new RestaurantView(dialogContent, dialog, entity);
    }

    @Override
    protected RestaurantDetail fetchDetails(Restaurant restaurant) {
        return webscrapingDetail.getRestaurantDetail(restaurant.getLienDetail());
    }
}
