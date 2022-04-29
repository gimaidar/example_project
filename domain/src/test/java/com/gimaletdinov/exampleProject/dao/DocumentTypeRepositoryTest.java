package com.gimaletdinov.exampleProject.dao;

import com.gimaletdinov.exampleProject.model.DocumentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.gimaletdinov.exampleProject.helper.DocumentTestHelper.getPopulateDocumentType;
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
    void findAll(){
        List<DocumentType>  documentTypesFromBd = documentTypeRepository.findAll();
        assertFalse(documentTypesFromBd.isEmpty());
        assertTrue(documentTypesFromBd.size() == 1);
        assertEquals(testDocumentTypeInBD, documentTypesFromBd.get(0));
    }

    @Test
    @Transactional
    void findByCode(){
        DocumentType documentTypeFromBd = documentTypeRepository.findByCode(testDocumentTypeInBD.getCode()).get();
        assertNotNull(documentTypeFromBd);
        assertEquals(testDocumentTypeInBD, documentTypeFromBd);
    }

}