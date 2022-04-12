package com.gimaletdinov.exampleProject.service;

import com.gimaletdinov.exampleProject.dao.DocumentRepository;
import com.gimaletdinov.exampleProject.model.Document;

/**
 * Класс реализация интерфейса DocumentService. Реализация методов получения данных с БД и преобразования данных в формат ответа
 */
public class DocumentServiceImpl implements DocumentService {

    private final DocumentRepository documentRepository;

    public DocumentServiceImpl(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    /**
     * @see DocumentService#Document(Document) 
     * @param document
     */
    @Override
    public void Document(Document document) {
        documentRepository.save(document);
    }
}
