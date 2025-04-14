package fr.nova.novascrape.model.base;

public class Supermarket extends BaseEntity {
    private final String detailsUrl;

    public Supermarket(String nom, String adresse, String detailsUrl) {
        super(nom, adresse);
        this.detailsUrl = detailsUrl;
    }

    public String getDetailsUrl() {
        return detailsUrl;
    }
}
