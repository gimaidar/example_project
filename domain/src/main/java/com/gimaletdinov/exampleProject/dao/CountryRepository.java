package com.gimaletdinov.exampleProject.dao;

import com.gimaletdinov.exampleProject.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Интерфейс репозитория для сущности Страна
 */
@Repository
public interface CountryRepository extends JpaRepository<Country, Integer> {
    Optional<Country> findByCode(String code);
}
