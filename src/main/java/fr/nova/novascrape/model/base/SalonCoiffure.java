package fr.nova.novascrape.model.base;

public class SalonCoiffure {
    String nom;
    String adresse;
    String prixServices;
    String lienDetail;

    public SalonCoiffure(String nom, String adresse, String prixServices, String lienDetail) {
        this.nom = nom;
        this.adresse = adresse;
        this.prixServices = prixServices;
        this.lienDetail = lienDetail;
    }

    @Override
    public String toString() {
        return "SalonCoiffure{" +
                "nom='" + nom + '\'' +
                ", adresse='" + adresse + '\'' +
                ", prixServices='" + prixServices + '\'' +
                ", lienDetail='" + lienDetail + '\'' +
                '}';
    }
}