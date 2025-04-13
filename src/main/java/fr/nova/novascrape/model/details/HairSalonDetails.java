package fr.nova.novascrape.model.details;


public class HairSalonDetails {
    String nom;
    String adresse;
    String horaire;
    String tarif;


    public HairSalonDetails(String nom, String adresse, String horaire, String tarif) {
        this.nom = nom;
        this.adresse = adresse;
        this.horaire = horaire;
        this.tarif = tarif;
    }

    public String getNom() {
        return nom;
    }

    public String getAdresse() {
        return adresse;
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
