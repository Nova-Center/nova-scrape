package fr.nova.novascrape.controller;

import fr.nova.novascrape.model.base.Restaurant;
import fr.nova.novascrape.model.details.RestaurantDetail;
import fr.nova.novascrape.view.*;
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
    protected Function<Restaurant, CardView<Restaurant>> getCardFactory() {
        return RestaurantView::new;
    }

    @Override
    protected DetailView<RestaurantDetail> createDetailView() {
        return new RestaurantDetailView(dialogContent, dialog);
    }

    @Override
    protected RestaurantDetail fetchDetails(Restaurant restaurant) {
        return webscrapingDetail.getRestaurantDetail(restaurant.getLienDetail());
    }
}
