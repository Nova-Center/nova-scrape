package fr.nova.novascrape.model.details;

import java.util.Objects;

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

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        BaseEntityDetail that = (BaseEntityDetail) o;
        return Objects.equals(nom, that.nom) && Objects.equals(adresse, that.adresse);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom, adresse);
    }
}
