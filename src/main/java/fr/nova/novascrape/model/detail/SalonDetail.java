package fr.nova.novascrape.model.detail;


public class SalonDetail {
    String nom;
    String adresse;
    String horaire;
    String tarif;



    public SalonDetail(String nom, String adresse, String horaire, String tarif) {
        this.nom = nom;
        this.adresse = adresse;
        this.horaire = horaire;

        this.tarif = tarif;
    }

    @Override
    public String toString() {
        return "Salon : " + nom + "\n" +
                "Adresse : " + adresse + "\n" +
                "Horaires : \n" + horaire + "\n" +

                "Tarifs : \n" + tarif;
    }


}