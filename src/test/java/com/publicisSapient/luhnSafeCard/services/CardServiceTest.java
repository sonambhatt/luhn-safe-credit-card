package com.publicisSapient.luhnSafeCard.services;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.publicisSapient.luhnSafeCard.models.Card;
import com.publicisSapient.luhnSafeCard.repositories.CardRepository;
import com.publicisSapient.luhnSafeCard.utils.CardValidator;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class CardServiceTest {
    @InjectMocks CardService service;
    @Mock CardRepository repository;
    @Mock CardValidator validator;

    @Test
    void shouldRegister() {
        Card card = new Card("", "randomName","randomNumber", BigDecimal.ZERO);
        Card registeredCard = new Card(UUID.randomUUID().toString(), "randomName","randomNumber", BigDecimal.ZERO);

        Mockito.when(repository.save(card)).thenReturn(registeredCard);

        assertEquals(registeredCard, service.register(card));
        Mockito.verify(validator).performBasicValidation(card);
        Mockito.verify(validator).performLuhnValidation(card);
    }

    @Test
    void shouldGetAllCards() {
        List<Card> allCards = Collections.singletonList(new Card("", "randomName", "randomNumber", BigDecimal.ZERO));
        Mockito.when(repository.findAll()).thenReturn(allCards);

        assertEquals(allCards, service.getAll());
    }
}
