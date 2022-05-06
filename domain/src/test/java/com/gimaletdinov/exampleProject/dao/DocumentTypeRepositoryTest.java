package com.gimaletdinov.exampleProject.dao;

import com.gimaletdinov.exampleProject.model.DocumentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.gimaletdinov.exampleProject.helper.DocumentTestHelper.getPopulateDocumentType;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
class DocumentTypeRepositoryTest {

    private DocumentType testDocumentTypeInBD;

    @Autowired
    private DocumentTypeRepository documentTypeRepository;

    @BeforeEach
    void saveTestCountryInBD(){
        testDocumentTypeInBD = documentTypeRepository.save(getPopulateDocumentType());
    }

    @Test
    @Transactional
    @DisplayName("Тест: findAll (Найти все типы документов)")
    void findAll(){
        List<DocumentType>  documentTypesFromBd = documentTypeRepository.findAll();
        assertThat(documentTypesFromBd).isNotEmpty();
        assertThat(documentTypesFromBd).hasSize(1);
        assertThat(documentTypesFromBd.get(0)).isEqualTo(testDocumentTypeInBD);
    }

    @Test
    @Transactional
    @DisplayName("Тест: findByCode (Найти тип документа по коду)")
    void findByCode(){
        DocumentType documentTypeFromBd = documentTypeRepository.findByCode(testDocumentTypeInBD.getCode()).get();
        assertThat(documentTypeFromBd).isNotNull();
        assertThat(documentTypeFromBd).isEqualTo(testDocumentTypeInBD);
    }

}