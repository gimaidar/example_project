package com.gimaletdinov.exampleProject.dao;

import com.gimaletdinov.exampleProject.model.security.AuthorizationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorizationUserRepository extends JpaRepository<AuthorizationUser, Integer> {
    Optional<AuthorizationUser> findByEmail(String email);
}
