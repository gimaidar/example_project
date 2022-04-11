package com.gimaletdinov.exampleProject.dao;

import com.gimaletdinov.exampleProject.model.DocumentType;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Интерфейс репозитория для сущности Тип документа
 */
public interface DocumentTypeRepository extends JpaRepository<DocumentType, Integer> {
}
