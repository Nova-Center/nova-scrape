package fr.nova.novascrape.test;

import fr.nova.novascrape.model.base.HairSalon;
import fr.nova.novascrape.model.base.Restaurant;
import fr.nova.novascrape.model.base.Supermarket;
import fr.nova.novascrape.service.WebScrapingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
class WebScrapingServiceTest {
    private WebScrapingService webScrapingService;
    private Restaurant restaurant;
    private Supermarket supermarket;
    private HairSalon hairSalon;

    @BeforeEach
    void setUp() {
        this.webScrapingService = new WebScrapingService();
        supermarket = new Supermarket("Monop'","rue de la Roquette 75011 Paris 0,08km","https://www.bonial.fr/Magasins/Paris/Monop-rue-de-la-Roquette/v-f247138930");
        hairSalon = new HairSalon("Barber Shop Number One","16 Place de la Nation, 75012 Paris","N/A","https://www.treatwell.fr/salon/barber-shop-number-one/");
        restaurant = new Restaurant("KHUN AKORN","8, AVENUE DE TAILLEBOURG 75011 PARIS 11ème Nation","Thai","https://www.lesrestos.com/restaurant/fiche/paris/khun-akorn","Si l'adresse est discrète, et le décor exotique et recherché, on vient pour la cuisine de spécialités thaïes qui explique le déplacement des nombreux...");
    }

    @Test
    void getSupermarkets() {

        List<Supermarket> markets =  webScrapingService.getSupermarkets();
        Supermarket market = markets.get(0);
        //verify Supermarket object are good
        assertEquals(market,supermarket);
        //verify when Supermarket object are bad
        assertNotEquals(market,supermarket.setNom("Monopolie"));
    }

    @Test
    void getHairSalons() {
        List<HairSalon> salon  = webScrapingService.getHairSalons();
        HairSalon hairSalonRes = salon.get(0);

        assertEquals(hairSalon,hairSalonRes);
        assertNotEquals(hairSalon,hairSalonRes.setAddress("10 rue des peintres"));
    }

    @Test
    void getRestaurants() {
        List<Restaurant> restaurants = webScrapingService.getRestaurants();
        Restaurant restaurant = restaurants.get(0);
        assertEquals(restaurant,restaurant);
        assertNotEquals(restaurant,restaurant.setAddress("10 rue des zingoingoin"));
    }

    @Test
    void getGarageQuartier() {
    }
}
