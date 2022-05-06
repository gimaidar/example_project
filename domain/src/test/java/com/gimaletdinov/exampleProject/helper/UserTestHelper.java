package com.gimaletdinov.exampleProject.helper;

import com.gimaletdinov.exampleProject.model.Country;
import com.gimaletdinov.exampleProject.model.Document;
import com.gimaletdinov.exampleProject.model.DocumentType;
import com.gimaletdinov.exampleProject.model.Office;
import com.gimaletdinov.exampleProject.model.User;

import static com.gimaletdinov.exampleProject.helper.CountryTestHelper.getPopulateCountry;
import static com.gimaletdinov.exampleProject.helper.DocumentTestHelper.getPopulateDocument;
import static com.gimaletdinov.exampleProject.helper.DocumentTestHelper.getPopulateDocumentType;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class UserTestHelper {
    public final static Integer TEST_USER_ID = 1;
    public final static String TEST_USER_FIRST_NAME = "test_user_first_name";
    public final static String TEST_USER_SECOND_NAME = "test_user_second_name";
    public final static String TEST_USER_MIDDLE_NAME = "test_user_middle_name";
    public final static Integer TEST_USER_POSITION = 1 ;
    private final static String TEST_USER_PHONE = "99999999999";
    private final static boolean TEST_USER_IS_IDENTIFIED = true;

    private final static String TEST_UPDATED_USER_FIRST_NAME = "test_updared_user_first_name";
    private final static String TEST_UPDATED_USER_SECOND_NAME = "test_updared_user_second_name";
    private final static String TEST_UPDATED_USER_MIDDLE_NAME = "test_updared_user_middle_name";
    private final static Integer TEST_UPDATED_USER_POSITION = 1 ;
    private final static String TEST_UPDATED_USER_PHONE = "888888888888";
    private final static boolean TEST_UPDATED_USER_IS_IDENTIFIED = true;

    public static User getPopulateUser() {
        User user = new User();
        user.setId(TEST_USER_ID);
        user.setFirstName(TEST_USER_FIRST_NAME);
        user.setSecondName(TEST_USER_SECOND_NAME);
        user.setMiddleName(TEST_USER_MIDDLE_NAME);
        user.setPosition(TEST_USER_POSITION);
        user.setPhone(TEST_USER_PHONE);
        user.setIsIdentified(TEST_USER_IS_IDENTIFIED);

        Office office = OfficeTestHelper.getPopulateOffice();
        user.setOffice(office);

        Document document = getPopulateDocument();
        document.setUser(user);
        user.setDocument(document);
        return user;
    }

    public static User getUserForUpdate(User user) {
        user.setId(TEST_USER_ID);
        user.setFirstName(TEST_UPDATED_USER_FIRST_NAME);
        user.setSecondName(TEST_UPDATED_USER_SECOND_NAME);
        user.setMiddleName(TEST_UPDATED_USER_MIDDLE_NAME);
        user.setPosition(TEST_UPDATED_USER_POSITION);
        user.setPhone(TEST_UPDATED_USER_PHONE);
        user.setIsIdentified(TEST_UPDATED_USER_IS_IDENTIFIED);
        return user;
    }
}
