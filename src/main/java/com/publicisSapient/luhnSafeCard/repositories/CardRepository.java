package com.publicisSapient.luhnSafeCard.repositories;

import org.springframework.data.repository.CrudRepository;

import com.publicisSapient.luhnSafeCard.models.Card;

public interface CardRepository extends CrudRepository<Card, String> {
}
