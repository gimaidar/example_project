package com.gimaletdinov.exampleProject.dao;

import com.gimaletdinov.exampleProject.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<Document, Integer> {
}
