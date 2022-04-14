package com.gimaletdinov.exampleProject.dao;

import com.gimaletdinov.exampleProject.model.Office;
import com.gimaletdinov.exampleProject.model.User;

import static com.gimaletdinov.exampleProject.dao.OfficeTestHelper.TEST_OFFICE_ID;
import static com.gimaletdinov.exampleProject.dao.OfficeTestHelper.TEST_UPDATED_OFFICE_ID;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class UserTestHelper {
    // Проверь файл data.sql данные должны совпадать
    public final static Integer TEST_USER_ID = 1;
    private final static String TEST_USER_FIRST_NAME = "test_user_first_name";
    private final static String TEST_USER_SECOND_NAME = "test_user_second_name";
    private final static String TEST_USER_MIDDLE_NAME = "test_user_middle_name";
    private final static Integer TEST_USER_POSITION = 1 ;
    private final static String TEST_USER_PHONE = "99999999999";
    private final static boolean TEST_USER_IS_IDENTIFIED = true;

    public final static Integer TEST_COUNTRY_ID = 643;

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
    
    public static void assertUsersEquals(User expected, User actual){
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getFirstName(), actual.getFirstName());
        assertEquals(expected.getSecondName(), actual.getSecondName());
        assertEquals(expected.getMiddleName(), actual.getMiddleName());
        assertEquals(expected.getPosition(), actual.getPosition());
        assertEquals(expected.getPhone(), actual.getPhone());
        assertEquals(expected.getIsIdentified(), actual.getIsIdentified());
    }
}
