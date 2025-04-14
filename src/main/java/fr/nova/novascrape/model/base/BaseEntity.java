package fr.nova.novascrape.model.base;

public abstract class BaseEntity {
    protected final String nom;
    protected final String addresse;

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
}
