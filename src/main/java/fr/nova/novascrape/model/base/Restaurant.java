package fr.nova.novascrape.model.base;

public  class Restaurant {
    String nom;
    String adresse;
    String typeCuisine;
    String lienDetail;
    String description;

    public Restaurant(String nom, String adresse, String typeCuisine, String lienDetail, String description) {
        this.nom = nom;
        this.adresse = adresse;
        this.typeCuisine = typeCuisine;
        this.lienDetail = lienDetail;
        this.description = description;
    }

    public String getNom() {
        return nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getTypeCuisine() {return typeCuisine;}

    public String getLienDetail() {return lienDetail;}

    public String getDescription() {return description;}



    @Override
    public String toString() {
        return "Nom: " + nom + ", Adresse: " + adresse + ", Type de cuisine: " + typeCuisine + ", Lien: " + lienDetail + ", Description: " + description;
    }

}
