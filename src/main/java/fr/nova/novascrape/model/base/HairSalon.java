package fr.nova.novascrape.model.base;

public class HairSalon {
    String nom;
    String adresse;
    String prixServices;
    String lienDetail;

    public HairSalon(String nom, String adresse, String prixServices, String lienDetail) {
        this.nom = nom;
        this.adresse = adresse;
        this.prixServices = prixServices;
        this.lienDetail = lienDetail;
    }

    public String getNom() {
        return nom;
    }
    public String getAdresse() {
        return adresse;
    }
    public String getPrixServices() {return prixServices;}
    public String getLienDetail() {return lienDetail;}

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
