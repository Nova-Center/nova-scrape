package fr.nova.novascrape.service;

import fr.nova.novascrape.model.detail.RestaurantDetail;
import fr.nova.novascrape.model.detail.SalonDetail;
import fr.nova.novascrape.model.detail.SupermarketDetails;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class WebScrapingDetail {

    //Récuperation du detail via l'url de detail (pour les restaurants)
    public void recupererDetailRestaurant(String urlDetail) {
        RestaurantDetail restaurant = null;

        try {
            Document doc = Jsoup.connect(urlDetail).get();

            String nom = doc.select("h1").first().text().split(" à ")[0];
            String adresse = doc.select("div.adresse").text();
            String metro = doc.select("div.metro").text();
            String telephone = doc.select("div.bloc_tel_mobiyo_container a").text();
            String typeCuisine = doc.select("tr.line:contains(Type de cuisine) a").text();
            String genreEtablissement = doc.select("tr.line:contains(Genre d\\\'établissement) .right").text();
            String fermetureHebdo = doc.select("tr.line:contains(Fermeture hebdo) .right").text();
            String prixMenu = doc.select("tr.line:contains(Prix du menu/carte) .right").text();
            String guides = doc.select("tr.line:contains(Cité dans les guides) .right").text();

            StringBuilder services = new StringBuilder();
            Elements serviceElements = doc.select("div.servicePrestation .option .boutonOpt.selected span");
            for (Element serviceElement : serviceElements) {
                services.append(serviceElement.text()).append(", ");
            }

            String commentaires = doc.select("div.commentaire.article").text();
            restaurant = new RestaurantDetail(nom, adresse, metro, telephone, typeCuisine, genreEtablissement, fermetureHebdo, prixMenu, guides, services.toString(), commentaires);

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(restaurant);
    }



    //Récuperation du detail via l'url de detail (pour les salon)
    public void recupererDetailSalon(String urlDetail) {
        SalonDetail salon = null;

        try {
            Document doc = Jsoup.connect(urlDetail).get();

            String nom = doc.select("h1.Text-module_smHero__2uXfi").text();
            String adresse = doc.select("span[data-cy=VenueAddress]").text();

            // Horaires
            StringBuilder horaires = new StringBuilder();
            Elements horaireElements = doc.select("script[type=application/ld+json]");

            for (Element horaireElement : horaireElements) {
                if (horaireElement.toString().contains("\"openingHoursSpecification\"")) {
                    String jsonString = horaireElement.data().toString();
                    JSONObject jsonObject = new JSONObject(jsonString);
                    JSONArray graphArray = jsonObject.getJSONArray("@graph");

                    for (int i = 0; i < graphArray.length(); i++) {
                        JSONObject graphObject = graphArray.getJSONObject(i);
                        if (graphObject.has("openingHoursSpecification")) {
                            JSONArray openingHoursArray = graphObject.getJSONArray("openingHoursSpecification");
                            for (int j = 0; j < openingHoursArray.length(); j++) {
                                JSONObject openingHoursObject = openingHoursArray.getJSONObject(j);
                                JSONArray dayOfWeekArray = openingHoursObject.getJSONArray("dayOfWeek");
                                String opens = openingHoursObject.getString("opens");
                                String closes = openingHoursObject.getString("closes");

                                horaires.append(dayOfWeekArray.join(", ")).append(": ").append(opens).append(" - ").append(closes).append("\n");
                            }
                        }
                    }
                }
            }

            // Tarifs
            StringBuilder tarifs = new StringBuilder();
            Elements tarifElements = doc.select("div.MenuItem-module--menu-item--ddf3a1");
            for (Element tarifElement : tarifElements) {
                String nomTarif = tarifElement.select("span.Text-module_body__2lxF8").text();
                String prixTarif = tarifElement.select("span.Text-module_bodyHeavy__1LMI1").text();

                if (!nomTarif.isEmpty() && !prixTarif.isEmpty()) {
                    tarifs.append("- ").append(nomTarif).append(" : ").append(prixTarif).append("\n");
                }
            }

            salon = new SalonDetail(nom, adresse, horaires.toString(), tarifs.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(salon);
    }

//    //Récuperation du detail via l'url de detail (pour les supermarcge) (pas completement focntionnel)
    public void recupererDetailSupermarche(String urlDetail) {
        SupermarketDetails supermarche = null;

        try {
            Document doc = Jsoup.connect(urlDetail).get();

            // Nom du supermarché
            String nom = doc.select("h1").first().text().split(" - ")[0];

            // Adresse
            String adresse = doc.select("p.address").text();
            String telephone = doc.select("a.phone").text();
            StringBuilder horaires = new StringBuilder();
            Elements horaireElements = doc.select("table.opening-hours tbody tr");
            for (Element horaireElement : horaireElements) {
                String jour = horaireElement.select("td:first-child").text();
                String heure = horaireElement.select("td:last-child").text();
                horaires.append(jour).append(": ").append(heure).append("\n");
            }

            // Création de l'objet SupermarcheDetail
            supermarche = new SupermarketDetails(nom, adresse, telephone, horaires.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(supermarche);
    }

}
