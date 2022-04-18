package com.gimaletdinov.exampleProject.dao;

import com.gimaletdinov.exampleProject.dto.request.UserListRequestDto;
import com.gimaletdinov.exampleProject.dto.request.UserSaveRequestDto;
import com.gimaletdinov.exampleProject.dto.request.UserUpdateRequestDto;
import com.gimaletdinov.exampleProject.model.Country;
import com.gimaletdinov.exampleProject.model.Office;
import com.gimaletdinov.exampleProject.model.User;

import static com.gimaletdinov.exampleProject.dao.DocumentTestHelper.TEST_DOCUMENT_DATE;
import static com.gimaletdinov.exampleProject.dao.DocumentTestHelper.TEST_DOCUMENT_NAME;
import static com.gimaletdinov.exampleProject.dao.DocumentTestHelper.TEST_DOCUMENT_NUMBER;
import static com.gimaletdinov.exampleProject.dao.DocumentTestHelper.TEST_DOCUMENT_TYPE_ID;
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
    public final static String TEST_COUNTRY_NAME = "Российская Федерация";

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

    public static UserListRequestDto getPopulateUserListRequestDto() {
        UserListRequestDto userListRequestDto = new UserListRequestDto();
        userListRequestDto.setOfficeId(TEST_OFFICE_ID);
        userListRequestDto.setFirstName(TEST_USER_FIRST_NAME);
        userListRequestDto.setSecondName(TEST_USER_SECOND_NAME);
        userListRequestDto.setMiddleName(TEST_USER_MIDDLE_NAME);
        userListRequestDto.setPosition(TEST_USER_POSITION);
        userListRequestDto.setDocCode(TEST_DOCUMENT_TYPE_ID);
        userListRequestDto.setCountryCode(TEST_COUNTRY_ID);
        return userListRequestDto;
    }

    public static UserUpdateRequestDto getPopulateUserUpdateRequestDto() {
        UserUpdateRequestDto userUpdateRequestDto = new UserUpdateRequestDto();
        userUpdateRequestDto.setId(TEST_USER_ID);
        userUpdateRequestDto.setFirstName(TEST_USER_FIRST_NAME);
        userUpdateRequestDto.setSecondName(TEST_USER_SECOND_NAME);
        userUpdateRequestDto.setMiddleName(TEST_USER_MIDDLE_NAME);
        userUpdateRequestDto.setPosition(TEST_USER_POSITION);
        userUpdateRequestDto.setPhone(TEST_USER_PHONE);
        userUpdateRequestDto.setIsIdentified(TEST_USER_IS_IDENTIFIED);
        userUpdateRequestDto.setOfficeId(TEST_OFFICE_ID);
        userUpdateRequestDto.setDocName(TEST_DOCUMENT_NAME);
        userUpdateRequestDto.setDocNumber(TEST_DOCUMENT_NUMBER);
        userUpdateRequestDto.setDocDate(TEST_DOCUMENT_DATE);
        userUpdateRequestDto.setCountryCode(TEST_COUNTRY_ID);
        userUpdateRequestDto.setCountryName(TEST_COUNTRY_NAME);
        return userUpdateRequestDto;
    }

    public static UserSaveRequestDto getPopulateUserSaveRequestDto() {
        UserSaveRequestDto userSaveRequestDto = new UserSaveRequestDto();
        userSaveRequestDto.setFirstName(TEST_USER_FIRST_NAME);
        userSaveRequestDto.setSecondName(TEST_USER_SECOND_NAME);
        userSaveRequestDto.setMiddleName(TEST_USER_MIDDLE_NAME);
        userSaveRequestDto.setPosition(TEST_USER_POSITION);
        userSaveRequestDto.setPhone(TEST_USER_PHONE);
        userSaveRequestDto.setIsIdentified(TEST_USER_IS_IDENTIFIED);
        userSaveRequestDto.setOfficeId(TEST_OFFICE_ID);
        userSaveRequestDto.setDocCode(TEST_DOCUMENT_TYPE_ID);
        userSaveRequestDto.setDocName(TEST_DOCUMENT_NAME);
        userSaveRequestDto.setDocNumber(TEST_DOCUMENT_NUMBER);
        userSaveRequestDto.setDocDate(TEST_DOCUMENT_DATE);
        userSaveRequestDto.setCountryCode(TEST_COUNTRY_ID);
        userSaveRequestDto.setCountryName(TEST_COUNTRY_NAME);
        return userSaveRequestDto;
    }

    public static Country getPopulateCountry(){
        Country country = new Country();
        country.setId(TEST_COUNTRY_ID);
        country.setName(TEST_COUNTRY_NAME);
        return country;
    }
}
