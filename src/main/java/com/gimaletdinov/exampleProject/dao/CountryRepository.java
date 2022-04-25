package com.gimaletdinov.exampleProject.dao;

import com.gimaletdinov.exampleProject.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Интерфейс репозитория для сущности Страна
 */
public interface CountryRepository extends JpaRepository<Country, Integer> {
    Optional<Country> findByCode(String code);
}
