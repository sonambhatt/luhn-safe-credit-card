package com.publicisSapient.luhnSafeCard.utils;

import org.junit.jupiter.api.Test;

import com.publicisSapient.luhnSafeCard.exceptions.InvalidCardException;
import com.publicisSapient.luhnSafeCard.models.Card;

import static org.junit.jupiter.api.Assertions.assertThrows;

class CardValidatorTest {

    CardValidator validator = card -> { };

    @Test
    void shouldThrowIfCardIsNull() {
        Card card = null;
        assertThrows(InvalidCardException.class, () -> validator.performBasicValidation(card));
    }

    @Test
    void shouldThrowIfCardNumberIsNullOrEmpty() {
        Card card = new Card();
        assertThrows(InvalidCardException.class, () -> validator.performBasicValidation(card));

        card.setNumber("");
        assertThrows(InvalidCardException.class, () -> validator.performBasicValidation(card));
    }

    @Test
    void shouldThrowIfCardNumberIsLongerThanAllowed() {
        Card card = new Card();
        card.setNumber("11112222333344445555");
        assertThrows(InvalidCardException.class, () -> validator.performBasicValidation(card));
    }

    @Test
    void shouldThrowIfCardNumberHasNonDigitCharacters() {
        Card card = new Card();
        card.setNumber("111122aa45555");
        assertThrows(InvalidCardException.class, () -> validator.performBasicValidation(card));
    }
}
