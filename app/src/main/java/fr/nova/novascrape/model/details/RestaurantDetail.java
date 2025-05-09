package fr.nova.novascrape.model.details;

import java.util.Objects;

public class RestaurantDetail extends BaseEntityDetail {
    String metro;
    String telephone;
    String typeCuisine;
    String genreEtablissement;
    String fermetureHebdo;
    String prixMenu;
    String guide;
    String services;
    String commentaires;

    public RestaurantDetail(String nom, String adresse, String metro, String telephone, String typeCuisine, String genreEtablissement, String fermetureHebdo, String prixMenu, String guide, String services, String commentaires) {
        super(nom, adresse);
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

    public String getMetro() {
        return metro;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getTypeCuisine() {
        return typeCuisine;
    }

    public String getGenreEtablissement() {
        return genreEtablissement;
    }

    public String getFermetureHebdo() {
        return fermetureHebdo;
    }

    public String getPrixMenu() {
        return prixMenu;
    }

    public String getGuide() {
        return guide;
    }

    public String getServices() {
        return services;
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        RestaurantDetail that = (RestaurantDetail) o;
        return Objects.equals(metro, that.metro) && Objects.equals(telephone, that.telephone) && Objects.equals(typeCuisine, that.typeCuisine) && Objects.equals(genreEtablissement, that.genreEtablissement) && Objects.equals(fermetureHebdo, that.fermetureHebdo) && Objects.equals(prixMenu, that.prixMenu) && Objects.equals(guide, that.guide) && Objects.equals(services, that.services) && Objects.equals(commentaires, that.commentaires);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), metro, telephone, typeCuisine, genreEtablissement, fermetureHebdo, prixMenu, guide, services, commentaires);
    }
}
