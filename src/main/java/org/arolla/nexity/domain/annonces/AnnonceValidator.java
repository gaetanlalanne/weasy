package org.arolla.nexity.domain.annonces;

import org.arolla.nexity.domain.annonces.adresses.Adresse;
import org.springframework.util.StringUtils;

public class AnnonceValidator {
    private AnnonceId annonceId;
    private String titre;
    private String descriptif;
    private Adresse adresse;
    private Double surface;
    private Double prix;

    public AnnonceValidator withAnnonceId(AnnonceId annonceId) {
        this.annonceId = annonceId;
        return this;
    }

    public AnnonceValidator withTitre(String titre) {
        this.titre = titre;
        return this;
    }

    public AnnonceValidator withDescriptif(String descriptif) {
        this.descriptif = descriptif;
        return this;
    }

    public AnnonceValidator withAdresse(Adresse adresse) {
        this.adresse = adresse;
        return this;
    }

    public AnnonceValidator withSurface(Double surface) {
        this.surface = surface;
        return this;
    }

    public AnnonceValidator withPrix(Double prix) {
        this.prix = prix;
        return this;
    }

    public boolean isInvalid() {
        return annonceId == null || StringUtils.isEmpty(titre) || StringUtils.isEmpty(descriptif) || adresse == null || surface == 0D || prix == 0D;
    }

    public Annonce createAnnonce() {
        return new Annonce(annonceId, titre, descriptif, adresse, surface, prix);
    }
}