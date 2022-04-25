package com.gimaletdinov.exampleProject.service;

import com.gimaletdinov.exampleProject.dto.response.DocumentTypeListResponseDto;
import com.gimaletdinov.exampleProject.model.DocumentType;

import java.util.List;

/**
 * Интерфейс сервиса для сущности Тип документа
 */
public interface DocumentTypeService {

    /**
     * Метод для получения всех типов документа
     * @return список типов документа
     */
    List<DocumentTypeListResponseDto> getAllDocumentTypes();

    /**
     * Метод для получения типа документа по code
     * @param code
     * @return тип документа
     */
    DocumentType getDocumentTypeByCode(String code);
}
