package org.arolla.nexity.domain.annonces.adresses;

import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Optional;

public class AdresseValidator {
    private String libelle;
    private String codePostal;
    private String ville;

    public AdresseValidator withLibelle(String libelle) {
        this.libelle = libelle;
        return this;
    }

    public AdresseValidator withCodePostal(String codePostal) {
        this.codePostal = codePostal;
        return this;
    }

    public AdresseValidator withVille(String ville) {
        this.ville = ville;
        return this;
    }

    public Optional<AdresseValidationError> isInvalid() {
        if (StringUtils.isEmpty(ville)) {
            return Optional.of(AdresseValidationError.INVALID_VILLE);
        }
        if (StringUtils.isEmpty(libelle)) {
            return Optional.of(AdresseValidationError.INVALID_LIBELLE);
        }
        if (CodePostalValidator.create().withCodePostal(codePostal).isInvalid())  {
            return Optional.of(AdresseValidationError.INVALID_CODE_POSTAL);
        }
        return Optional.empty();
    }

    public Adresse createAdresse() {
        CodePostal codePostal = CodePostalValidator.create().withCodePostal(this.codePostal).createCodePostal();
        return new Adresse(this.libelle, codePostal, ville);
    }
}