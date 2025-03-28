package fr.nova.novascrape.service;

import fr.nova.novascrape.model.Commerce;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class webScrapingService {
    public List<Commerce> recupererCommercesProches(String url) {
        List<Commerce> commerces = new ArrayList<>();

        try {
            Document doc = Jsoup.connect(url).get();

            // Sélection des éléments contenant les commerces (selon votre capture d'écran)
            Elements storeElements = doc.select("a.inline-block.border-b");

            for (Element storeElement : storeElements) {
                String nom = storeElement.select("div.font-semibold").text();
                String adresse = storeElement.select("div.text-sm").text();
                String detailsUrl = "https://www.bonial.fr" + storeElement.attr("href");

                commerces.add(new Commerce(nom, adresse, detailsUrl));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return commerces;
    }
}


/*exemple d'utilisation
*         WebScrapingService service = new WebScrapingService();
        String urlListeCommerces = "https://www.bonial.fr/Magasins/Paris/Supermarches/v-c5";
        List<Commerce> commerces = service.recupererCommercesProches(urlListeCommerces);

        if (commerces.isEmpty()) {
            System.out.println("Aucun commerce trouvé. Vérifiez si la structure HTML a changé.");
        } else {
            for (Commerce commerce : commerces) {
                System.out.println(commerce);
            }
        }
    }*/