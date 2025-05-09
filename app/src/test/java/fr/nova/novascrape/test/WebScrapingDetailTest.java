package fr.nova.novascrape.test;

import fr.nova.novascrape.model.base.Supermarket;
import fr.nova.novascrape.model.details.HairSalonDetail;
import fr.nova.novascrape.model.details.RestaurantDetail;
import fr.nova.novascrape.model.details.SupermarketDetail;
import fr.nova.novascrape.service.WebScrapingDetail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WebScrapingDetailTest {

    private WebScrapingDetail webScrapingDetail;
    SupermarketDetail supermarketDetail;
    HairSalonDetail hairSalonDetail;
    RestaurantDetail restaurantDetail;
    Supermarket supermarket;

    @BeforeEach
    void setUp() {
        this.webScrapingDetail = new WebScrapingDetail();
        supermarket = new Supermarket("Monop'","rue de la Roquette 75011 Paris 0,08km","https://www.bonial.fr/Magasins/Paris/Monop-rue-de-la-Roquette/v-f247138930");
        supermarketDetail = new SupermarketDetail("","","Téléphone non trouvé","Lundi07:00-22:00\n" +
                "Mardi07:00-22:00\n" +
                "Mercredi07:00-22:00\n" +
                "Jeudi07:00-22:00\n" +
                "Vendredi07:00-22:00\n" +
                "Samedi07:00-22:00\n" +
                "Dimanche09:00-13:00");
        hairSalonDetail = new HairSalonDetail("BarberShop Lagny","1 Rue de Lagny, Paris, 75020","\"Monday\", \"Tuesday\", \"Wednesday\", \"Thursday\", \"Friday\", \"Saturday\", \"Sunday\": 09:30 - 20:00","- Homme - Shampoing, coupe et coiffage 20 min Ma prestation en détail... Économisez jusqu'à 15% : à partir de 17 € Sélectionner\n" +
                "- Homme - Taille de la barbe 15 min Ma prestation en détail... Économisez jusqu'à 15% : à partir de 12,75 € Sélectionner\n" +
                "- Homme - Coupe et taille de la barbe 30 min Ma prestation en détail... Économisez jusqu'à 15% : à partir de 29,75 € Sélectionner\n" +
                "- Enfant - Coupe 20 min Ma prestation en détail... Économisez jusqu'à 15% : à partir de 12,75 € Sélectionner\n" +
                "- Homme - Coloration de la barbe 10 min Ma prestation en détail... Économisez jusqu'à 15% : à partir de 12,75 € Sélectionner\n" +
                "- Homme - Shampoing, coupe et coiffage 20 min Ma prestation en détail... Économisez jusqu'à 15% : à partir de 17 € Sélectionner\n" +
                "- Homme - Coupe et taille de la barbe 30 min Ma prestation en détail... Économisez jusqu'à 15% : à partir de 29,75 € Sélectionner\n" +
                "- Homme - Coloration de la barbe 10 min Ma prestation en détail... Économisez jusqu'à 15% : à partir de 12,75 € Sélectionner\n" +
                "- Homme - Taille de la barbe 15 min Ma prestation en détail... Économisez jusqu'à 15% : à partir de 12,75 € Sélectionner\n");
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
                "Un Atelier de Joël Robuchon Etoile est installé depuis décembre 2010 dans le drugstore Publicis et se voit attribuer 2 étoiles Michelin en février 2016 jusqu'en 2021 passé à 1 étoile. Le grand chef Joël Robuchon ajoute les Champs-Elysées à sa liste d'adresses prestigieuses. Le restaurant L'Atelier Robuchon Etoile est sous la Direction du chef Eric Bouchenoire, Meilleur Ouvrier de France et bras droit du chef triple étoilé depuis 35 ans, il est assisté par Moreno Rizzo, son bras droit en cuisine et Renaud Baconnais chef pâtissier. Selon la saison, la carte du chef Eric Bouchenoire pour le restaurant L'Atelier Robuchon Etoile annonce le foie gras de canard, la gelée de caviar dans une crème de chou fleur, la ventrèche de saumon aux grenailles confites, l'encornet à l'artichaut violet et chorizo, la langoustine en papillote croustillante au basilic, l'oursin et son crémeux de wasabi, le black cod dans un bouillon de daïkon aux restes de yuzu, les Saint-Jacques au lard paysan, le merlan Colbert, la caille farcie au foie gras et caramélisée avec une pomme purée, ou l'agneau de Lozère à la fleur de thym avec sa pomme purée. Pour le dessert, on se laisse tenter par le clafoutis à la mangue, le \"parfum des îles\", ou la symphonie chocolatée avec compote de mendiants à la liqueur de mirabelle. La carte des vins est conseillée par le sommelier Patrice Aignan Lassagne. L'accueil charmant et le service délicieux du restaurant L'Atelier Robuchon Etoile sont sous la Direction de Hassen Rais. Menus du déjeuner à 49Euro, 69Euro et 89Euro. A la carte du restaurant L'Atelier Robuchon Etoile compter environ de 90Euro à 210Euro. Menu végétarien à 99Euro. Menu dégustation à 199Euro. Service, du mardi au jeudi de 12h à 15h et de 19h jusqu'à 23h30 ; vendredi et samedi de 12h à 15h30 et de 19h à minuit. Dans la salle du sous-sol du restaurant L'Atelier Robuchon Etoile on trouve une décoration en noir et rouge et un grand comptoir, plus quelques tables. Réservation conseillée pour le déjeuner de 11h30 à 12h30 ; pour le dîner à 18h30 uniquement. Voir l'avis et les photos de l'Atelier Joël Robuchon Etoile."
        );
    }

    @Test
    void getRestaurantDetail() {
        String url = "https://www.lesrestos.com/restaurant/fiche/paris/l-atelier-etoile-de-joel-robuchon";
        RestaurantDetail restaurant = webScrapingDetail.getRestaurantDetail(url);

        assertEquals(restaurantDetail, restaurant);
        assertNotEquals(restaurantDetail, restaurant.setAdresse("Le Adresse de Joel Robuchon"));
    }

    @Test
    void getHairSalonDetail() {
        String url= "https://www.treatwell.fr/salon/barbershop-lagny/";
        HairSalonDetail hairSalon = webScrapingDetail.getHairSalonDetail(url);

        assertEquals(hairSalon, hairSalon);
        assertNotEquals(hairSalonDetail,hairSalon.setNom("coiffeur de folie"));
    }

    @Test
    void getSupermarketDetail() {
        String url= "https://www.bonial.fr/Magasins/Paris/Carrefour-Proximite-Boulevard-Beaumarchais/v-f542700402";
        SupermarketDetail market = webScrapingDetail.getSupermarketDetail(supermarket);

        assertEquals(supermarketDetail, market);
        assertNotEquals(supermarketDetail,market.setAdresse("Le Adresse de Joel Robuchon"));
    }
}
