package com.publicisSapient.luhnSafeCard.controllers;

import lombok.RequiredArgsConstructor;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.publicisSapient.luhnSafeCard.models.Card;
import com.publicisSapient.luhnSafeCard.models.CardRequestBody;
import com.publicisSapient.luhnSafeCard.models.CardResponseBody;
import com.publicisSapient.luhnSafeCard.services.CardService;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/cards", produces = MediaType.APPLICATION_JSON_VALUE)
public class CardController {

    private final CardService service;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CardResponseBody> registerCard(@RequestBody @Valid CardRequestBody card) {

        return ResponseEntity.status(HttpStatus.CREATED).body(service.register(card));
    }

    @GetMapping
    public ResponseEntity<List<CardResponseBody>> fetchCards() {
        return ResponseEntity.ok(service.getAll());
    }
}
