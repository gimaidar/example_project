package com.gimaletdinov.exampleProject.dao;

import com.gimaletdinov.exampleProject.model.DocumentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Интерфейс репозитория для сущности Тип документа
 */
@Repository
public interface DocumentTypeRepository extends JpaRepository<DocumentType, Integer> {
    Optional<DocumentType> findByCode(String code);
}
