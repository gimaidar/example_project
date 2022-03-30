package com.gimaletdinov.exampleProject.service;

import com.gimaletdinov.exampleProject.dto.response.DocumentTypeListResponseDto;

import java.util.List;

public interface DocumentTypeService {

    List<DocumentTypeListResponseDto> getAllDocumentTypes();
}
