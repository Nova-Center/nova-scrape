package fr.nova.novascrape.model.base;

public  class Supermarket {
    private final String nom;
    private final String adresse;
    private final String detailsUrl;

    public Supermarket(String nom, String adresse, String detailsUrl) {
        this.nom = nom;
        this.adresse = adresse;
        this.detailsUrl = detailsUrl;
    }

    public String getNom() {
        return nom;
    }

    public String getAdresse() {
        return adresse;
    }
}
