package org.arolla.nexity.infrastructure.models;

public class AdresseModel {
    private String libelle;
    private String codePostal;
    private String ville;

    public AdresseModel() {
    }

    public AdresseModel(String libelle, String codePostal, String ville) {
        this.libelle = libelle;
        this.codePostal = codePostal;
        this.ville = ville;
    }

    public String getLibelle() {
        return libelle;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public String getVille() {
        return ville;
    }
}
