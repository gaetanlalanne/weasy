package org.arolla.nexity.domain.annonces;

import org.arolla.nexity.domain.annonces.adresses.Adresse;
import org.springframework.util.StringUtils;

import java.util.Objects;

/**
 * Une annonce immobilière.
 */
public class Annonce {

    private AnnonceId annonceId;
    private String titre;
    private String descriptif;
    private Adresse adresse;
    private Double surface;
    private Double prix;

    public Annonce(AnnonceId annonceId, String titre, String descriptif, Adresse adresse, Double surface, Double prix) {

        if (annonceId == null || StringUtils.isEmpty(titre) || StringUtils.isEmpty(descriptif) || surface == 0D || prix == 0D) {
            //TODO: Make separate errors for each parameter
            throw new IllegalArgumentException("Illegal annonce");
        }

        this.annonceId = annonceId;
        this.titre = titre;
        this.descriptif = descriptif;
        this.adresse = adresse;
        this.surface = surface;
        this.prix = prix;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Annonce annonce = (Annonce) o;
        return Objects.equals(annonceId, annonce.annonceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(annonceId);
    }

    public AnnonceId getAnnonceId() {
        return annonceId;
    }

    public String getVille() {
        return adresse.getVille();
    }
}
