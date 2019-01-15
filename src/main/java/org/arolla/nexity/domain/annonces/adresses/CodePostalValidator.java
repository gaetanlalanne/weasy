package org.arolla.nexity.domain.annonces.adresses;

import org.apache.tomcat.util.file.Matcher;

import java.util.regex.Pattern;

public class CodePostalValidator {
    private String codePostal;

    public static CodePostalValidator create() {
        return new CodePostalValidator();
    }

    public CodePostalValidator withCodePostal(String codePostal) {
        this.codePostal = codePostal;
        return this;
    }

    public boolean isInvalid() {
        return !Pattern.compile("^(?:(?:(?:0[1-9]|[1-8]\\d|9[0-4])(?:\\d{3})?)|97[1-8]|98[4-9]|\u200C\u200B\u200C\u200B2[abAB])$").matcher(codePostal).find();
    }

    public CodePostal createCodePostal() {
        if (isInvalid()) {
            throw new IllegalArgumentException("This zip code is invalid");
        }
        return new CodePostal(codePostal);
    }

}