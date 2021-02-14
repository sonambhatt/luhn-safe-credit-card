package com.publicisSapient.luhnSafeCard.utils;

import com.publicisSapient.luhnSafeCard.exceptions.InvalidCardException;
import com.publicisSapient.luhnSafeCard.models.Card;

public interface CardValidator {

    int CARD_NUMBER_LENGTH_LIMIT_MAX = 19;

    void performLuhnValidation(Card card);

    default void performBasicValidation(Card card) {
        if (card == null) throw new InvalidCardException("Card data cannot be null");

        if (card.getNumber() == null || "".equals(card.getNumber())) throw new InvalidCardException("Card number cannot be empty or null");

        if (card.getNumber().length() > CARD_NUMBER_LENGTH_LIMIT_MAX) throw new InvalidCardException("Card number cannot be longer than 19 characters");

        if (card.getNumber().chars().anyMatch(Character::isDigit)) throw new InvalidCardException("Card number must contain only digits");
    }
}
