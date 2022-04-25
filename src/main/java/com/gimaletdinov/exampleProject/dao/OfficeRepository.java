package com.gimaletdinov.exampleProject.dao;

import com.gimaletdinov.exampleProject.model.Office;
import com.gimaletdinov.exampleProject.model.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * Интерфейс репозитория для сущности Офис
 */
public interface OfficeRepository extends JpaRepository<Office, Integer>, JpaSpecificationExecutor<Office> {
}
