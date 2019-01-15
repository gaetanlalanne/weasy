package org.arolla.nexity.domain.annonces;

import java.util.Objects;
import java.util.UUID;

/**
 * Identifiant unique d'une agence immobilière sous forme de UUID.
 */
public class AnnonceId {
    private UUID annonceId;

    public AnnonceId() {
    }

    public AnnonceId(UUID annonceId) {
        this.annonceId = annonceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnnonceId annonceId1 = (AnnonceId) o;
        return Objects.equals(annonceId, annonceId1.annonceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(annonceId);
    }

    @Override
    public String toString() {
        return annonceId.toString();
    }

    public UUID getAnnonceId() {
        return annonceId;
    }
}
