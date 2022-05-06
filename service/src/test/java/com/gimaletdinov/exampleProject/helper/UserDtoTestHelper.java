package com.gimaletdinov.exampleProject.helper;

import com.gimaletdinov.exampleProject.dto.request.UserListRequestDto;
import com.gimaletdinov.exampleProject.dto.request.UserSaveRequestDto;
import com.gimaletdinov.exampleProject.dto.request.UserUpdateRequestDto;
import com.gimaletdinov.exampleProject.dto.response.UserListResponseDto;
import com.gimaletdinov.exampleProject.dto.response.UserResponseDto;
import com.gimaletdinov.exampleProject.model.Country;
import com.gimaletdinov.exampleProject.model.Document;
import com.gimaletdinov.exampleProject.model.DocumentType;
import com.gimaletdinov.exampleProject.model.Office;
import com.gimaletdinov.exampleProject.model.User;


import java.util.ArrayList;
import java.util.List;

import static com.gimaletdinov.exampleProject.helper.CountryDtoTestHelper.getPopulateCountry;
import static com.gimaletdinov.exampleProject.helper.DocumentDtoTestHelper.getPopulateDocument;
import static com.gimaletdinov.exampleProject.helper.DocumentDtoTestHelper.getPopulateDocumentType;
import static com.gimaletdinov.exampleProject.helper.OfficeDtoTestHelper.getPopulateOffice;


public class UserDtoTestHelper {
    public final static Integer TEST_USER_ID = 1;
    private final static String TEST_USER_FIRST_NAME = "test_user_first_name";
    private final static String TEST_USER_SECOND_NAME = "test_user_second_name";
    private final static String TEST_USER_MIDDLE_NAME = "test_user_middle_name";
    private final static Integer TEST_USER_POSITION = 1 ;
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

        Office office = getPopulateOffice();
        user.setOffice(office);

        Document document = getPopulateDocument();
        DocumentType documentType = getPopulateDocumentType();
        document.setDocumentType(documentType);

        document.setUser(user);
        user.setDocument(document);

        Country country = getPopulateCountry();
        user.setCountry(country);
        return user;
    }

    public static UserListRequestDto getPopulateUserListRequestDto() {
        UserListRequestDto userListRequestDto = new UserListRequestDto();
        userListRequestDto.setOfficeId(OfficeDtoTestHelper.TEST_OFFICE_ID);
        userListRequestDto.setFirstName(TEST_USER_FIRST_NAME);
        userListRequestDto.setSecondName(TEST_USER_SECOND_NAME);
        userListRequestDto.setMiddleName(TEST_USER_MIDDLE_NAME);
        userListRequestDto.setPosition(TEST_USER_POSITION);
        userListRequestDto.setDocCode(DocumentDtoTestHelper.TEST_DOCUMENT_TYPE_CODE);
        userListRequestDto.setCountryCode(CountryDtoTestHelper.TEST_COUNTRY_CODE);
        return userListRequestDto;
    }

    public static UserUpdateRequestDto getPopulateUserUpdateRequestDto() {
        UserUpdateRequestDto userUpdateRequestDto = new UserUpdateRequestDto();
        userUpdateRequestDto.setId(TEST_USER_ID);
        userUpdateRequestDto.setFirstName(TEST_UPDATED_USER_FIRST_NAME);
        userUpdateRequestDto.setSecondName(TEST_UPDATED_USER_SECOND_NAME);
        userUpdateRequestDto.setMiddleName(TEST_UPDATED_USER_MIDDLE_NAME);
        userUpdateRequestDto.setPosition(TEST_UPDATED_USER_POSITION);
        userUpdateRequestDto.setPhone(TEST_UPDATED_USER_PHONE);
        userUpdateRequestDto.setIsIdentified(TEST_UPDATED_USER_IS_IDENTIFIED);
        userUpdateRequestDto.setOfficeId(OfficeDtoTestHelper.TEST_OFFICE_ID);
        userUpdateRequestDto.setDocName(DocumentDtoTestHelper.TEST_DOCUMENT_NAME);
        userUpdateRequestDto.setDocNumber(DocumentDtoTestHelper.TEST_DOCUMENT_NUMBER);
        userUpdateRequestDto.setDocDate(DocumentDtoTestHelper.TEST_DOCUMENT_DATE);
        userUpdateRequestDto.setCountryCode(CountryDtoTestHelper.TEST_COUNTRY_CODE);
        userUpdateRequestDto.setCountryName(CountryDtoTestHelper.TEST_COUNTRY_NAME);
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
        userSaveRequestDto.setOfficeId(OfficeDtoTestHelper.TEST_OFFICE_ID);
        userSaveRequestDto.setDocCode(DocumentDtoTestHelper.TEST_DOCUMENT_TYPE_CODE);
        userSaveRequestDto.setDocName(DocumentDtoTestHelper.TEST_DOCUMENT_NAME);
        userSaveRequestDto.setDocNumber(DocumentDtoTestHelper.TEST_DOCUMENT_NUMBER);
        userSaveRequestDto.setDocDate(DocumentDtoTestHelper.TEST_DOCUMENT_DATE);
        userSaveRequestDto.setCountryCode(CountryDtoTestHelper.TEST_COUNTRY_CODE);
        userSaveRequestDto.setCountryName(CountryDtoTestHelper.TEST_COUNTRY_NAME);
        return userSaveRequestDto;
    }

    public static List<UserListResponseDto> getPopulateUserListResponseDtoList() {
        UserListResponseDto userListResponseDto = new UserListResponseDto();
        userListResponseDto.setId(TEST_USER_ID);
        userListResponseDto.setFirstName(TEST_USER_FIRST_NAME);
        userListResponseDto.setSecondName(TEST_USER_SECOND_NAME);
        userListResponseDto.setMiddleName(TEST_USER_MIDDLE_NAME);
        userListResponseDto.setPosition(TEST_USER_POSITION);

        List<UserListResponseDto> userListResponseDtoList = new ArrayList<>();
        userListResponseDtoList.add(userListResponseDto);
        return userListResponseDtoList;
    }

    public static UserResponseDto getPopulateUserResponseDto() {
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setOfficeId(OfficeDtoTestHelper.TEST_OFFICE_ID);
        userResponseDto.setFirstName(TEST_USER_FIRST_NAME);
        userResponseDto.setSecondName(TEST_USER_SECOND_NAME);
        userResponseDto.setMiddleName(TEST_USER_MIDDLE_NAME);
        userResponseDto.setPosition(TEST_USER_POSITION);
        userResponseDto.setPhone(TEST_USER_PHONE);
        userResponseDto.setIsIdentified(TEST_USER_IS_IDENTIFIED);
        userResponseDto.setOfficeId(OfficeDtoTestHelper.TEST_OFFICE_ID);
        userResponseDto.setDocName(DocumentDtoTestHelper.TEST_DOCUMENT_NAME);
        userResponseDto.setDocNumber(DocumentDtoTestHelper.TEST_DOCUMENT_NUMBER);
        userResponseDto.setDocDate(DocumentDtoTestHelper.TEST_DOCUMENT_DATE);
        userResponseDto.setCountryCode(CountryDtoTestHelper.TEST_COUNTRY_CODE);
        userResponseDto.setCountryName(CountryDtoTestHelper.TEST_COUNTRY_NAME);
        return userResponseDto;
    }
}
