package com.publicisSapient.luhnSafeCard.utils.luhn;

import org.junit.jupiter.api.Test;

import com.publicisSapient.luhnSafeCard.exceptions.InvalidCardException;
import com.publicisSapient.luhnSafeCard.models.Card;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LuhnCardValidatorTest {

    LuhnCardValidator validator = new LuhnCardValidator();

    @Test
    void shouldValidateCards() {
        Card card = new Card();
        card.setNumber("79927398713");
        assertDoesNotThrow(() -> validator.validate(card));
        card.setNumber("79927398710");
        assertThrows(InvalidCardException.class, () -> validator.validate(card));
    }
}
