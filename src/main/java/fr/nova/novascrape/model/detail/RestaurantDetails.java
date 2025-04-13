package fr.nova.novascrape.model.detail;

public class RestaurantDetails {
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

    public RestaurantDetails(String nom, String adresse, String metro, String telephone, String typeCuisine, String genreEtablissement, String fermetureHebdo, String prixMenu, String guide, String services, String commentaires) {
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

    public String getNom() {return nom;}
    public String getAdresse() {return adresse;}
    public String getMetro() {return metro;}
    public String getTelephone() {return telephone;}
    public String getTypeCuisine() {return typeCuisine;}
    public String getGenreEtablissement() {return genreEtablissement;}
    public String getFermetureHebdo() {return fermetureHebdo;}
    public String getPrixMenu() {return prixMenu;}
    public String getGuide() {return guide;}
    public String getServices() {return services;}

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
