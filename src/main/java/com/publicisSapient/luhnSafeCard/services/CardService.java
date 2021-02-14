package com.publicisSapient.luhnSafeCard.services;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import com.publicisSapient.luhnSafeCard.models.Card;
import com.publicisSapient.luhnSafeCard.models.CardRequestBody;
import com.publicisSapient.luhnSafeCard.models.CardResponseBody;
import com.publicisSapient.luhnSafeCard.repositories.CardRepository;
import com.publicisSapient.luhnSafeCard.utils.CardValidator;

@RequiredArgsConstructor
@Service
public class CardService {

    private final CardRepository repository;
    private final CardValidator validator;

    public CardResponseBody register(CardRequestBody cardRequestBody) {
        Card card = new Card(cardRequestBody);
        validator.performBasicValidation(card);
        validator.performLuhnValidation(card);

        Card savedCard = repository.save(card);

        return new CardResponseBody(savedCard);
    }

    public List<CardResponseBody> getAll() {
        return StreamSupport.stream(repository.findAll().spliterator(), false).map(CardResponseBody::new).collect(Collectors.toList());
    }
}
