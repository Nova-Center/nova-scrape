package fr.nova.novascrape.model.detail;

public class SupermarketDetails {
    String telephone;
    String horaires;

    public SupermarketDetails( String telephone, String horaires) {
        this.telephone = telephone;
        this.horaires = horaires;
    }
    public String getTelephone() {return telephone;}
    public String getHoraires() {return horaires;}

    @Override
    public String toString() {
        return
                "Téléphone : " + telephone + "\n" +
                "Horaires d'ouverture : \n" + horaires;
    }
}
