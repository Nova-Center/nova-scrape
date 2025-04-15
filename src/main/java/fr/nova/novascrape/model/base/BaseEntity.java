package fr.nova.novascrape.model.base;

public abstract class BaseEntity {
    protected String nom;
    protected String addresse;

    public BaseEntity(String nom, String addresse) {
        this.nom = nom;
        this.addresse = addresse;
    }

    public String getNom() {
        return nom;
    }
    public String getAddress() {
        return addresse;
    }

    public String setNom(String nom) {return this.nom = nom;}
    public String setAddress(String addresse) {return this.addresse = addresse;}
}
