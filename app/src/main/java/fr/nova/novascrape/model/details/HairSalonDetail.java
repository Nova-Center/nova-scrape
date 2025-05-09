package fr.nova.novascrape.model.details;


import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof HairSalonDetail that)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(horaire, that.horaire) && Objects.equals(tarif, that.tarif);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), horaire, tarif);
    }
}
