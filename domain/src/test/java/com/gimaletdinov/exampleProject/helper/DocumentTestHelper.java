package com.gimaletdinov.exampleProject.helper;

import com.gimaletdinov.exampleProject.model.Document;
import com.gimaletdinov.exampleProject.model.DocumentType;

import java.time.LocalDate;


public class DocumentTestHelper {
    public final static String TEST_DOCUMENT_TYPE_CODE = "21";
    public final static Integer TEST_DOCUMENT_TYPE_ID = 1;

    public final static Integer TEST_DOCUMENT_ID = 1;
    public final static String TEST_DOCUMENT_NAME = "Паспорт";
    public final static Integer TEST_DOCUMENT_NUMBER = 111111;
    public final static LocalDate TEST_DOCUMENT_DATE = LocalDate.of(2022, 04, 12);

    public static Document getPopulateDocument() {
        Document document = new Document();
        document.setId(TEST_DOCUMENT_TYPE_ID);
        document.setNumber(TEST_DOCUMENT_NUMBER);
        document.setDate(TEST_DOCUMENT_DATE);
        return document;
    }

    public static DocumentType getPopulateDocumentType() {
        DocumentType documentType = new DocumentType();
        documentType.setId(TEST_DOCUMENT_ID);
        documentType.setCode(TEST_DOCUMENT_TYPE_CODE);
        documentType.setName(TEST_DOCUMENT_NAME);
        return documentType;
    }
}
