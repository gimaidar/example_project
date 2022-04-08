package com.gimaletdinov.exampleProject.service;

import com.gimaletdinov.exampleProject.dto.response.DocumentTypeListResponseDto;
import com.gimaletdinov.exampleProject.model.DocumentType;

import java.util.List;

public interface DocumentTypeService {

    List<DocumentTypeListResponseDto> getAllDocumentTypes();

    DocumentType getDocumentTypeById(int id);
}
