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

    public String setNom(String nom){return this.nom = nom;}

    public String setAdresse(String adresse) {return this.adresse = adresse;}
}
