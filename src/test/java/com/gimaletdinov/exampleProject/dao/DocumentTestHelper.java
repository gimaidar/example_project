package com.gimaletdinov.exampleProject.dao;

import com.gimaletdinov.exampleProject.dto.response.DocumentTypeListResponseDto;
import com.gimaletdinov.exampleProject.model.Document;
import com.gimaletdinov.exampleProject.model.DocumentType;
import com.gimaletdinov.exampleProject.model.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.gimaletdinov.exampleProject.dao.UserTestHelper.TEST_USER_ID;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class DocumentTestHelper {
    // Проверь файл data.sql данные должны совпадать
        public final static Integer TEST_DOCUMENT_TYPE_ID = 21;

    public final static String TEST_DOCUMENT_NAME = "Паспорт";

    public final static Integer TEST_DOCUMENT_NUMBER = 111111;
    public final static LocalDate TEST_DOCUMENT_DATE = LocalDate.of(2022, 04, 12);

    public static Document getPopulateDocument() {
        Document document = new Document();
        document.setNumber(TEST_DOCUMENT_NUMBER);
        document.setDate(TEST_DOCUMENT_DATE);
        return document;
    }

    public static DocumentType getPopulateDocumentType() {
        DocumentType documentType = new DocumentType();
        documentType.setId(TEST_DOCUMENT_TYPE_ID);
        documentType.setName(TEST_DOCUMENT_NAME);
        return documentType;
    }

    public static List<DocumentTypeListResponseDto> getPopulateDocumentTypeListResponseDtoList() {
        DocumentTypeListResponseDto documentTypeListResponseDto = new DocumentTypeListResponseDto();
        documentTypeListResponseDto.setCode(TEST_DOCUMENT_TYPE_ID);
        documentTypeListResponseDto.setName(TEST_DOCUMENT_NAME);

        List<DocumentTypeListResponseDto> documentTypeListResponseDtoList = new ArrayList<>();
        documentTypeListResponseDtoList.add(documentTypeListResponseDto);
        return documentTypeListResponseDtoList;
    }
}
