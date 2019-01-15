package org.arolla.nexity.infrastructure.models;

public class AnnonceCreationModel {

    private String titre;
    private String descriptif;
    private AdresseModel adresse;
    private Double surface;
    private Double prix;

    public AnnonceCreationModel() {
    }

    public AnnonceCreationModel(String titre, String descriptif, AdresseModel adresse, Double surface, Double prix) {
        this.titre = titre;
        this.descriptif = descriptif;
        this.adresse = adresse;
        this.surface = surface;
        this.prix = prix;
    }

    public String getTitre() {
        return titre;
    }

    public String getDescriptif() {
        return descriptif;
    }

    public AdresseModel getAdresse() {
        return adresse;
    }

    public Double getSurface() {
        return surface;
    }

    public Double getPrix() {
        return prix;
    }
}
