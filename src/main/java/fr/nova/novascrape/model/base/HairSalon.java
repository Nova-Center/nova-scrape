package fr.nova.novascrape.model.base;

public class HairSalon extends BaseEntity {
    String prixServices;
    String lienDetail;

    public HairSalon(String nom, String adresse, String prixServices, String lienDetail) {
        super(nom, adresse);
        this.prixServices = prixServices;
        this.lienDetail = lienDetail;
    }

    public String getPrixServices() {
        return prixServices;
    }

    public String getLienDetail() {
        return lienDetail;
    }

    @Override
    public String toString() {
        return "SalonCoiffure{" +
                "nom='" + this.nom + '\'' +
                ", adresse='" + this.nom + '\'' +
                ", prixServices='" + prixServices + '\'' +
                ", lienDetail='" + lienDetail + '\'' +
                '}';
    }
}
