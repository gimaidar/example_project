package com.gimaletdinov.exampleProject.service;

import com.gimaletdinov.exampleProject.dao.DocumentRepository;
import com.gimaletdinov.exampleProject.model.Document;

public class DocumentServiceImpl implements DocumentService {

    private final DocumentRepository documentRepository;

    public DocumentServiceImpl(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    @Override
    public void Document(Document document) {
        documentRepository.save(document);
    }
}
