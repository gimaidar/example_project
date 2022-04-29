package com.gimaletdinov.exampleProject.dao;

import com.gimaletdinov.exampleProject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Интерфейс репозитория для сущности Пользователь
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {
}
