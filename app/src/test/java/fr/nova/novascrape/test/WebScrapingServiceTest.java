package fr.nova.novascrape.test;

import fr.nova.novascrape.model.base.HairSalon;
import fr.nova.novascrape.model.base.Restaurant;
import fr.nova.novascrape.model.base.Supermarket;
import fr.nova.novascrape.service.WebScrapingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class WebScrapingServiceTest {

    @Mock
    private WebScrapingService webScrapingService;

    private Supermarket supermarket;

    private HairSalon hairSalon;

    private Restaurant restaurant;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        supermarket = new Supermarket("Monop'", "rue de la Roquette 75011 Paris 0,08km", "https://www.bonial.fr/Magasins/Paris/Monop-rue-de-la-Roquette/v-f247138930");
        hairSalon = new HairSalon("Barber Shop Number One", "16 Place de la Nation, 75012 Paris", "N/A", "https://www.treatwell.fr/salon/barber-shop-number-one/");
        restaurant = new Restaurant("KHUN AKORN", "8, AVENUE DE TAILLEBOURG 75011 PARIS 11ème Nation", "Thai", "https://www.lesrestos.com/restaurant/fiche/paris/khun-akorn", "Si l'adresse est discrète, et le décor exotique et recherché, on vient pour la cuisine de spécialités thaïes qui explique le déplacement des nombreux...");
    }

    @Test
    void getSupermarkets() {
        when(webScrapingService.getSupermarkets()).thenReturn(List.of(supermarket));

        List<Supermarket> markets = webScrapingService.getSupermarkets();

        Supermarket market = markets.get(0);

        // Vérification des assertions
        assertEquals(supermarket, market);
        assertNotEquals(supermarket.setNom("Monopolie"), market);
    }

    @Test
    void getHairSalons() {
        when(webScrapingService.getHairSalons()).thenReturn(List.of(hairSalon));

        List<HairSalon> salons = webScrapingService.getHairSalons();

        HairSalon salonRes = salons.get(0);

        assertEquals(hairSalon, salonRes);
        assertNotEquals(hairSalon.setAddress("10 rue des peintres"), salonRes);
    }

    @Test
    void getRestaurants() {
        when(webScrapingService.getRestaurants()).thenReturn(List.of(restaurant));

        List<Restaurant> restaurants = webScrapingService.getRestaurants();

        Restaurant restaurantRes = restaurants.get(0);

        assertEquals(restaurant, restaurantRes);
        assertNotEquals(restaurant.setAddress("10 rue des zingoingoin"), restaurantRes);
    }
}
