package fr.nova.novascrape.service;

import fr.nova.novascrape.db.dao.base.HairSalonDAO;
import fr.nova.novascrape.db.dao.base.RestaurantDAO;
import fr.nova.novascrape.db.dao.base.SupermarketDAO;
import fr.nova.novascrape.model.base.Restaurant;
import fr.nova.novascrape.model.base.HairSalon;
import fr.nova.novascrape.model.base.Supermarket;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WebScrapingService {
    private static final Logger log = LogManager.getLogger(WebScrapingService.class);

    public List<Supermarket> getSupermarkets() {
        List<Supermarket> supermarkets = new ArrayList<>();
        String url = "https://www.bonial.fr/Magasins/Paris/Supermarches/v-c5";
        try {
            Document doc = Jsoup.connect(url).get();
            Elements storeElements = doc.select("a.inline-block.border-b");

            for (Element storeElement : storeElements) {
                String nom = storeElement.select("div.font-semibold").text();
                String adresse = storeElement.select("div.text-sm").text();
                String detailsUrl = storeElement.attr("href");
                Supermarket supermarket = new Supermarket(nom, adresse, detailsUrl);
                supermarkets.add(supermarket);
                new SupermarketDAO().saveAndReturnId(supermarket);
            }
        } catch (IOException e) {
            log.error(e);
        }

        log.info("Supermarchés trouvés");

        return supermarkets;
    }

    public List<HairSalon> getHairSalons() {
        List<HairSalon> salons = new ArrayList<>();
        String url = "https://www.treatwell.fr/salons/soins-groupe-coiffure/offre-type-local/dans-metro-nation-fr/";

        try {
            Document doc = Jsoup.connect(url).get();

            // Sélection du script JSON
            Elements scriptElements = doc.select("script[type=application/ld+json]");

            if (!scriptElements.isEmpty()) {
                String jsonString = scriptElements.first().data();
                JSONObject jsonObject = new JSONObject(jsonString);
                JSONArray itemList = jsonObject.getJSONArray("itemListElement");

                for (int i = 0; i < itemList.length(); i++) {
                    JSONObject item = itemList.getJSONObject(i).getJSONObject("item");
                    String nom = item.getString("name");
                    JSONObject address = item.getJSONObject("address");
                    String streetAddress = address.getString("streetAddress");
                    String postalCode = address.has("postalCode") ? address.getString("postalCode") : "";
                    String addressLocality = address.has("addressLocality") ? address.getString("addressLocality") : ""; // Vérif ici

                    String adresse = streetAddress + ", " + postalCode + " " + addressLocality;
                    String lienDetail = item.getString("url");
                    String prix = "N/A";

                    HairSalon salon = new HairSalon(nom, adresse, prix, lienDetail);
                    salons.add(salon);
                    new HairSalonDAO().saveAndReturnId(salon);
                }
            } else {
                log.warn("Script JSON non trouvé");
            }

        } catch (IOException e) {
            log.error(e);
        }

        log.info("Salons de coiffure trouvés");

        return salons;
    }

    public List<Restaurant> getRestaurants() {
        List<Restaurant> restaurants = new ArrayList<>();
        String url = "https://www.lesrestos.com/restaurant/liste/paris/metro/nation";

        try {
            Document doc = Jsoup.connect(url).get();
            Elements restaurantElements = doc.select("div.item_list");

            for (Element restaurantElement : restaurantElements) {
                String nom = restaurantElement.select("h2.nom_resto").text();
                String adresse = restaurantElement.select("div.adresse").text();
                String typeCuisine = restaurantElement.select("div.type-cuisine a").text();
                String lienDetail = restaurantElement.select("div.img-resto a").attr("href");
                String description = restaurantElement.select("div.resume p").text();
                Restaurant restaurant = new Restaurant(nom, adresse, typeCuisine, lienDetail,description);
                restaurants.add(restaurant);
                new RestaurantDAO().saveAndReturnId(restaurant);
            }
        } catch (IOException e) {
            log.error(e);
        }

        log.info("Restaurants trouvés");

        return restaurants;
    }

    //Pour les infos du garage du quartier
    public void getGarageQuartier() {
        try {
            Document doc = Jsoup.connect("https://www.garage-st-antoine.com/garage-saint-antoine-specialiste-bmw-mini-paris-nation-qui-sommes-nous").get();
            Elements elements = doc.select("div.textwidget");

            // Nom du garage
            String nom = "Garage Saint-Antoine";

            // Adresse
            String adresse = elements.select("h3").first().text();

            // Téléphone
            String telephone = doc.select("a[href^=tel:]").attr("href").replace("tel:", "");

            // Affichage des informations
            log.info("Nom du garage : {}", nom);
            log.info("Adresse : {}", adresse);
            log.info("Téléphone : {}", telephone);

        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

}
