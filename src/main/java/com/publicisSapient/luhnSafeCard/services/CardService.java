package com.publicisSapient.luhnSafeCard.services;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import com.publicisSapient.luhnSafeCard.models.Card;
import com.publicisSapient.luhnSafeCard.repositories.CardRepository;
import com.publicisSapient.luhnSafeCard.utils.CardValidator;

@RequiredArgsConstructor
@Service
public class CardService {

    private final CardRepository repository;
    private final CardValidator validator;

    public Card register(Card card) {
        validator.performBasicValidation(card);
        validator.performLuhnValidation(card);
        return repository.save(card);
    }

    public List<Card> getAll() {
        return StreamSupport.stream(repository.findAll().spliterator(), false).collect(Collectors.toList());
    }
}
