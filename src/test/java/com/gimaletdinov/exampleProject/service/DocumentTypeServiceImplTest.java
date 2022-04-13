package com.gimaletdinov.exampleProject.service;

import com.gimaletdinov.exampleProject.dto.response.DocumentTypeListResponseDto;
import com.gimaletdinov.exampleProject.model.DocumentType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
class DocumentTypeServiceImplTest {

    @Autowired
    private DocumentTypeService documentTypeService;

    @Test
    @Transactional
    void getAllDocumentTypes() {
        List<DocumentTypeListResponseDto> resultList = documentTypeService.getAllDocumentTypes();
        assertTrue(resultList.size() > 0);
    }

    @Test
    @Transactional
    void getDocumentTypeById() {
        DocumentType documentType = documentTypeService.getDocumentTypeById(1);
        assertEquals(documentType.getId(), 1);
        assertNotNull(documentType);
    }
}