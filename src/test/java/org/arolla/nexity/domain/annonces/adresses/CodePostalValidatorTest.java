package org.arolla.nexity.domain.annonces.adresses;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class CodePostalValidatorTest {

    @Test
    public void canValidateAZipCode() {
        boolean zipCodeIsValid = new CodePostalValidator().withCodePostal("75002").isInvalid();

        assertThat(zipCodeIsValid).isFalse();
    }

    @Test
    public void aZipCodeWithLettersIsInvalid() {
        boolean zipCodeIsInvalid = new CodePostalValidator().withCodePostal("AERZE").isInvalid();

        assertThat(zipCodeIsInvalid).isTrue();
    }

    @Test
    public void aZipCodeWith3NumbersIsInvalid() {
        boolean zipCodeIsInvalid = new CodePostalValidator().withCodePostal("123").isInvalid();

        assertThat(zipCodeIsInvalid).isTrue();
    }
}