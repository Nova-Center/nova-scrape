package fr.nova.novascrape.test;

import fr.nova.novascrape.model.base.Supermarket;
import fr.nova.novascrape.model.details.HairSalonDetail;
import fr.nova.novascrape.model.details.RestaurantDetail;
import fr.nova.novascrape.model.details.SupermarketDetail;
import fr.nova.novascrape.service.WebScrapingDetail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class WebScrapingDetailTest {

    @Mock
    private WebScrapingDetail webScrapingDetail;

    private SupermarketDetail supermarketDetail;
    private HairSalonDetail hairSalonDetail;
    private RestaurantDetail restaurantDetail;
    private Supermarket supermarket;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        supermarket = new Supermarket("Monop'", "rue de la Roquette 75011 Paris 0,08km",
                "https://www.bonial.fr/Magasins/Paris/Monop-rue-de-la-Roquette/v-f247138930");

        supermarketDetail = new SupermarketDetail("", "", "Téléphone non trouvé",
                "Lundi07:00-22:00\n" +
                        "Mardi07:00-22:00\n" +
                        "Mercredi07:00-22:00\n" +
                        "Jeudi07:00-22:00\n" +
                        "Vendredi07:00-22:00\n" +
                        "Samedi07:00-22:00\n" +
                        "Dimanche09:00-13:00");

        hairSalonDetail = new HairSalonDetail("BarberShop Lagny", "1 Rue de Lagny, Paris, 75020",
                "\"Monday\", \"Tuesday\", \"Wednesday\", \"Thursday\", \"Friday\", \"Saturday\", \"Sunday\": 09:30 - 20:00",
                "- Homme - Shampoing, coupe et coiffage 20 min ...");

        restaurantDetail = new RestaurantDetail(
                "L'atelier Etoile De Joel Robuchon",
                "30, RUE VERNET 75008 PARIS 8ème",
                "Charles-de-Gaulle Etoile",
                "01 47 23 75 75",
                "Restaurant cuisine Gastronomique",
                "Restaurant",
                "dimanche midi et soir, lundi midi et soir",
                "À partir de 49 € jusqu'à 210 €",
                "Michelin, Pudlo, Lebey",
                "Climatisation, American Express, Service après 22h, Plats végétariens, Parking, CB - VISA EC - MC,",
                "Un Atelier de Joël Robuchon Etoile est installé depuis décembre 2010..."
        );
    }

    @Test
    void getRestaurantDetail() {
        String url = "https://www.lesrestos.com/restaurant/fiche/paris/l-atelier-etoile-de-joel-robuchon";

        when(webScrapingDetail.getRestaurantDetail(url)).thenReturn(restaurantDetail);

        RestaurantDetail result = webScrapingDetail.getRestaurantDetail(url);

        assertEquals(restaurantDetail, result);
    }

    @Test
    void getHairSalonDetail() {
        String url = "https://www.treatwell.fr/salon/barbershop-lagny/";

        when(webScrapingDetail.getHairSalonDetail(url)).thenReturn(hairSalonDetail);

        HairSalonDetail result = webScrapingDetail.getHairSalonDetail(url);

        assertEquals(hairSalonDetail, result);
    }

    @Test
    void getSupermarketDetail() {
        when(webScrapingDetail.getSupermarketDetail(supermarket)).thenReturn(supermarketDetail);

        SupermarketDetail result = webScrapingDetail.getSupermarketDetail(supermarket);

        assertEquals(supermarketDetail, result);
    }
}
