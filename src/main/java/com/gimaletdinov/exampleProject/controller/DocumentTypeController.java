package com.gimaletdinov.exampleProject.controller;

import com.gimaletdinov.exampleProject.dto.response.DocumentTypeListResponseDto;
import com.gimaletdinov.exampleProject.service.DocumentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *Контроллер для сущности Тип документа
 */
@RestController
@RequestMapping("api/docs")
public class DocumentTypeController {

    private final DocumentTypeService documentTypeService;

    @Autowired
    public DocumentTypeController(DocumentTypeService documentTypeService) {
        this.documentTypeService = documentTypeService;
    }

    /**
     * Метод возвращает список всех типов документа(справочник)
     * @return список всех типов документа(справочник)
     */
    @GetMapping()
    public List<DocumentTypeListResponseDto> getAllOrganizationsByPredicat() {
        return documentTypeService.getAllDocumentTypes();
    }
}
