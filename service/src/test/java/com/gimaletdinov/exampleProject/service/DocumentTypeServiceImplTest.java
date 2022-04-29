package com.gimaletdinov.exampleProject.service;

import com.gimaletdinov.exampleProject.dao.DocumentTypeRepository;
import com.gimaletdinov.exampleProject.dto.response.DocumentTypeListResponseDto;
import com.gimaletdinov.exampleProject.model.DocumentType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.gimaletdinov.exampleProject.helper.DocumentDtoTestHelper.TEST_DOCUMENT_TYPE_CODE;
import static com.gimaletdinov.exampleProject.helper.DocumentDtoTestHelper.getPopulateDocumentType;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
class DocumentTypeServiceImplTest {

    private DocumentType newTestDocumaentType = getPopulateDocumentType() ;

    @MockBean
    private DocumentTypeRepository documentTypeRepository;

    @Autowired
    private DocumentTypeService documentTypeService;

    @Test
    @Transactional
    void getAllDocumentTypes() {
        //Given
        DocumentType newDocumentType = getPopulateDocumentType();
        List<DocumentType> documentTypeList = new ArrayList<>();
        documentTypeList.add(newDocumentType);
        when(documentTypeRepository.findAll()).thenReturn(documentTypeList);

        //When
        List<DocumentTypeListResponseDto> resultList = documentTypeService.getAllDocumentTypes();

        //Then
        assertTrue(resultList.size() > 0);
    }

    @Test
    @Transactional
    void getDocumentTypeByCode() {
        //Given
        when(documentTypeRepository.findByCode(TEST_DOCUMENT_TYPE_CODE)).thenReturn(Optional.ofNullable(newTestDocumaentType));

        //When
        DocumentType documentTypeFromService = documentTypeService.getDocumentTypeByCode(TEST_DOCUMENT_TYPE_CODE);

        //Then
        assertEquals(TEST_DOCUMENT_TYPE_CODE, documentTypeFromService.getCode());
        assertNotNull(documentTypeFromService);
    }
}