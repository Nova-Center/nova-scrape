package fr.nova.novascrape.model;

    public class Commerce {
        private String nom;
        private String adresse;
        private String detailsUrl;

        public Commerce(String nom, String adresse, String detailsUrl) {
            this.nom = nom;
            this.adresse = adresse;
            this.detailsUrl = detailsUrl;
        }

        @Override
        public String toString() {
            return "Commerce{" +'\n' +
                    "nom=" + nom + '\n' +
                    "adresse=" + adresse + '\n' +
                    "detailsUrl=" + detailsUrl + '\n' +
                    '}';
        }
    }

