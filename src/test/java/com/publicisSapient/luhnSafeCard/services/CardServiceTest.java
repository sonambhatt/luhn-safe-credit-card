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
import com.publicisSapient.luhnSafeCard.models.CardRequestBody;
import com.publicisSapient.luhnSafeCard.models.CardResponseBody;
import com.publicisSapient.luhnSafeCard.repositories.CardRepository;
import com.publicisSapient.luhnSafeCard.utils.CardValidator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class CardServiceTest {
    @InjectMocks CardService service;
    @Mock CardRepository repository;
    @Mock CardValidator validator;

    @Test
    void shouldRegister() {
        CardRequestBody cardRequestBody = new CardRequestBody("randomName","randomNumber");
        Card card = new Card(cardRequestBody);
        Card savedCard = new Card(UUID.randomUUID().toString(), "randomName", "randomNumber", BigDecimal.ZERO);
        CardResponseBody registeredCard = new CardResponseBody(savedCard);

        Mockito.when(repository.save(any())).thenReturn(savedCard);

        assertEquals(registeredCard, service.register(cardRequestBody));
        Mockito.verify(validator).performBasicValidation(card);
        Mockito.verify(validator).performLuhnValidation(card);
    }

    @Test
    void shouldGetAllCards() {
        Card dummyCard = new Card("", "randomName", "randomNumber", BigDecimal.ZERO);
        List<Card> allCards = Collections.singletonList(dummyCard);
        Mockito.when(repository.findAll()).thenReturn(allCards);

        List<CardResponseBody> allResponses =
            Collections.singletonList(new CardResponseBody(dummyCard));

        assertEquals(allResponses, service.getAll());
    }
}
