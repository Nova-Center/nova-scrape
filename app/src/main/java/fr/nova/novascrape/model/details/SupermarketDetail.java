package fr.nova.novascrape.model.details;

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
}
