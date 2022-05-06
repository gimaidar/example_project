package com.gimaletdinov.exampleProject.service;

import com.gimaletdinov.exampleProject.dao.DocumentTypeRepository;
import com.gimaletdinov.exampleProject.dto.response.DocumentTypeListResponseDto;
import com.gimaletdinov.exampleProject.mapper.DocumentTypeMapperImpl;
import com.gimaletdinov.exampleProject.model.DocumentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.gimaletdinov.exampleProject.helper.DocumentDtoTestHelper.TEST_DOCUMENT_TYPE_CODE;
import static com.gimaletdinov.exampleProject.helper.DocumentDtoTestHelper.getPopulateDocumentType;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {DocumentTypeServiceImpl.class, DocumentTypeMapperImpl.class})
class DocumentTypeServiceImplTest {

    private DocumentType newTestDocumaentType = getPopulateDocumentType() ;

    @MockBean
    private DocumentTypeRepository documentTypeRepository;

    @Autowired
    private DocumentTypeService documentTypeService;

    @Test
    @DisplayName("Тест: getAllDocumentTypes (Найти все типы документов)")
    void getAllDocumentTypes() {
        //Given
        DocumentType newDocumentType = getPopulateDocumentType();
        List<DocumentType> documentTypeList = new ArrayList<>();
        documentTypeList.add(newDocumentType);
        when(documentTypeRepository.findAll()).thenReturn(documentTypeList);

        //When
        List<DocumentTypeListResponseDto> resultList = documentTypeService.getAllDocumentTypes();

        //Then
        assertThat(resultList).hasSizeGreaterThan(0);
    }

    @Test
    @DisplayName("Тест: getDocumentTypeByCode (Найти тип документа по коду)")
    void getDocumentTypeByCode() {
        //Given
        when(documentTypeRepository.findByCode(TEST_DOCUMENT_TYPE_CODE)).thenReturn(Optional.ofNullable(newTestDocumaentType));

        //When
        DocumentType documentTypeFromService = documentTypeService.getDocumentTypeByCode(TEST_DOCUMENT_TYPE_CODE);

        //Then
        assertThat(documentTypeFromService).isNotNull();
        assertThat(documentTypeFromService).isEqualTo(newTestDocumaentType);
    }
}