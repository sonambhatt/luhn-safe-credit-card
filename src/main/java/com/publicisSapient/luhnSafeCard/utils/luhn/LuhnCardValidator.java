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
    public void performLuhnValidation(Card card) {
        if (!isValidCreditCardNumber(card.getNumber())) {
            throw new InvalidCardException(String.format("Card number %s is not Luhn safe", card.getNumber()));
        }
    }

    public boolean isValidCreditCardNumber(String cardNumber) {

        List<Integer> cardDigits =
            cardNumber.chars().map(Character::getNumericValue).boxed().collect(Collectors.toList());

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
