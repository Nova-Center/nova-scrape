package fr.nova.novascrape.model.detail;

public class RestaurantDetail {
    String nom;
    String adresse;
    String metro;
    String telephone;
    String typeCuisine;
    String genreEtablissement;
    String fermetureHebdo;
    String prixMenu;
    String guide;
    String services;
    String commentaires;

    public  RestaurantDetail(String nom, String adresse, String metro, String telephone, String typeCuisine, String genreEtablissement, String fermetureHebdo, String prixMenu,String guide, String services, String commentaires) {
        this.nom = nom;
        this.adresse = adresse;
        this.metro = metro;
        this.telephone = telephone;
        this.typeCuisine = typeCuisine;
        this.genreEtablissement = genreEtablissement;
        this.fermetureHebdo = fermetureHebdo;
        this.prixMenu = prixMenu;
        this.guide = guide;
        this.services = services;
        this.commentaires = commentaires;
    }

    @Override
    public String toString() {
        return "Restaurant : " + nom + "\n" +
                "Adresse : " + adresse + "\n" +
                "Métro : " + metro + "\n" +
                "Téléphone : " + telephone + "\n" +
                "Type de cuisine : " + typeCuisine + "\n" +
                "Genre d'établissement : " + genreEtablissement + "\n" +
                "Fermeture hebdomadaire : " + fermetureHebdo + "\n" +
                "Prix du menu/carte : " + prixMenu + "\n" +
                "Cité dans les guides suivants: " + guide + "\n" +
                "Services et prestations : " + services + "\n" +
                "Commentaires : " + commentaires;
    }
}
