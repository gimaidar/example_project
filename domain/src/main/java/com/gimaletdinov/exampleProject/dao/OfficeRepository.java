package com.gimaletdinov.exampleProject.dao;

import com.gimaletdinov.exampleProject.model.Office;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Интерфейс репозитория для сущности Офис
 */
@Repository
public interface OfficeRepository extends JpaRepository<Office, Integer>, JpaSpecificationExecutor<Office> {
}
