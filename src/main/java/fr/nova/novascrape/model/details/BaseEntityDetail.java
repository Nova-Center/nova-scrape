package fr.nova.novascrape.model.details;

public abstract class BaseEntityDetail {
    protected String nom;
    protected String adresse;

    public BaseEntityDetail(String nom, String adresse) {
        this.nom = nom;
        this.adresse = adresse;
    }

    public String getNom() {
        return nom;
    }

    public String getAdresse() {
        return adresse;
    }
}
