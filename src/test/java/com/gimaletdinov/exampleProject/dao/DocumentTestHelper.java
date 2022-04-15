package com.gimaletdinov.exampleProject.dao;

import com.gimaletdinov.exampleProject.model.Document;
import com.gimaletdinov.exampleProject.model.User;

import java.time.LocalDate;

import static com.gimaletdinov.exampleProject.dao.UserTestHelper.TEST_USER_ID;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class DocumentTestHelper {
    // Проверь файл data.sql данные должны совпадать
        public final static Integer TEST_DOCUMENT_TYPE_ID = 21;

    private final static Integer TEST_DOCUMENT_NUMBER = 111111;
    private final static LocalDate TEST_DOCUMENT_DATE = LocalDate.of(2022, 04, 12);

    public static Document getPopulateDocument() {
        Document document = new Document();
        document.setNumber(TEST_DOCUMENT_NUMBER);
        document.setDate(TEST_DOCUMENT_DATE);
        return document;
    }
}
