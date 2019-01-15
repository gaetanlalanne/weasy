package org.arolla.nexity.domain.annonces.adresses;

/**
 * L'adresse d'une annonce
 */
public class Adresse {

    private String libelle;
    private CodePostal codePostal;
    // Needs validation to make sure all postings for the same city are grouped together
    private String ville;

    public Adresse(String libelle, CodePostal codePostal, String ville) {
        this.libelle = libelle;
        this.codePostal = codePostal;
        this.ville = ville;
    }

    public String getVille() {
        return ville;
    }
}
