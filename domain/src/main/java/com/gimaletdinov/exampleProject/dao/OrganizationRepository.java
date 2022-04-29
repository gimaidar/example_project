package com.gimaletdinov.exampleProject.dao;

import com.gimaletdinov.exampleProject.model.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Интерфейс репозитория для сущности Организация
 */
@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Integer>, JpaSpecificationExecutor<Organization> {
}
