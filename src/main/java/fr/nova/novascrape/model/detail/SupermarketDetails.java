package fr.nova.novascrape.model.detail;

public class SupermarketDetails {
    String telephone;
    String horaires;
    String nom;
    String address;

    public SupermarketDetails(String nom, String address, String telephone, String horaires) {
        this.telephone = telephone;
        this.horaires = horaires;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getHoraires() {
        return horaires;
    }

    public String getNom() {
        return nom;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return
                "Téléphone : " + telephone + "\n" +
                        "Horaires d'ouverture : \n" + horaires;
    }
}
