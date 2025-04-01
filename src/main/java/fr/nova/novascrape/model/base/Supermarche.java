package fr.nova.novascrape.model.base;

public  class Supermarche {
    public String nom;
    public String adresse;
    public String detailsUrl;

    public Supermarche(String nom, String adresse, String detailsUrl) {
        this.nom = nom;
        this.adresse = adresse;
        this.detailsUrl = detailsUrl;
    }
}