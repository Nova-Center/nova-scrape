package fr.nova.novascrape.model.detail;

public class SupermarketDetails {
    String nom;
    String adresse;
    String telephone;
    String horaires;

    public SupermarketDetails(String nom, String adresse, String telephone, String horaires) {
        this.nom = nom;
        this.adresse = adresse;
        this.telephone = telephone;
        this.horaires = horaires;
    }

    @Override
    public String toString() {
        return "Supermarché : " + nom + "\n" +
                "Adresse : " + adresse + "\n" +
                "Téléphone : " + telephone + "\n" +
                "Horaires d'ouverture : \n" + horaires;
    }
}
