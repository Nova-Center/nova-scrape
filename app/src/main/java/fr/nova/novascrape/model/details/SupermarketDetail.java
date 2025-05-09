package fr.nova.novascrape.model.details;

import java.util.Objects;

public class SupermarketDetail extends BaseEntityDetail {
    String telephone;
    String horaires;

    public SupermarketDetail(String nom, String address, String telephone, String horaires) {
        super(nom, address);
        this.telephone = telephone;
        this.horaires = horaires;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getHoraires() {
        return horaires;
    }

    @Override
    public String toString() {
        return
                "Téléphone : " + telephone + "\n" +
                        "Horaires d'ouverture : \n" + horaires;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof SupermarketDetail that)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(telephone, that.telephone) && Objects.equals(horaires, that.horaires);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), telephone, horaires);
    }
}
