package fr.nova.novascrape.model.base;

public class Restaurant extends BaseEntity {
    String typeCuisine;
    String lienDetail;
    String description;

    public Restaurant(String nom, String adresse, String typeCuisine, String lienDetail, String description) {
        super(nom, adresse);
        this.typeCuisine = typeCuisine;
        this.lienDetail = lienDetail;
        this.description = description;
    }

    public String getTypeCuisine() {
        return typeCuisine;
    }

    public String getLienDetail() {
        return lienDetail;
    }

    public String getDescription() {
        return description;
    }


    @Override
    public String toString() {
        return "Nom: " + this.nom + ", Adresse: " + this.addresse + ", Type de cuisine: " + typeCuisine + ", Lien: " + lienDetail + ", Description: " + description;
    }


}
