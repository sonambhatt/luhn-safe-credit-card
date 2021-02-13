package com.publicisSapient.luhnSafeCard.utils.luhn;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.publicisSapient.luhnSafeCard.exceptions.InvalidCardException;
import com.publicisSapient.luhnSafeCard.models.Card;
import com.publicisSapient.luhnSafeCard.utils.CardValidator;

@Component
public class LuhnCardValidator implements CardValidator {


    @Override
    public void validate(Card card) {
        if (!isValidCreditCardSequence(card.getNumber())) {
            throw new InvalidCardException(String.format("Card sequence %s is not valid", card.getNumber()));
        }
    }

    public boolean isValidCreditCardSequence(String cardSequence) {

        List<Integer> cardDigits =
            cardSequence.chars().map(Character::getNumericValue).boxed().collect(Collectors.toList());

        for (int index = cardDigits.size() - 2; index >= 0; index -= 2) {
            int num = 2 * cardDigits.get(index);

            if (num > 9) {
                num = num % 10 + num / 10;
            }

            cardDigits.set(index, num);
        }

        int sum = cardDigits.stream().mapToInt(Integer::intValue).sum();

        return sum % 10 == 0;
    }
}
