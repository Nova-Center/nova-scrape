package fr.nova.novascrape.model.details;


public class HairSalonDetail extends BaseEntityDetail {
    String horaire;
    String tarif;


    public HairSalonDetail(String nom, String adresse, String horaire, String tarif) {
        super(nom, adresse);
        this.horaire = horaire;
        this.tarif = tarif;
    }

    public String getHoraire() {
        return horaire;
    }
    public String getTarif() {
        return tarif;
    }

    @Override
    public String toString() {
        return "Salon : " + nom + "\n" +
                "Adresse : " + adresse + "\n" +
                "Horaires : \n" + horaire + "\n" +
                "Tarifs : \n" + tarif;
    }
}
