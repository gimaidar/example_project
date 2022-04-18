package com.gimaletdinov.exampleProject.service;

import com.gimaletdinov.exampleProject.controller.handler.exceptions.NoSuchObjectException;
import com.gimaletdinov.exampleProject.dao.UserRepository;
import com.gimaletdinov.exampleProject.dto.request.UserListRequestDto;
import com.gimaletdinov.exampleProject.dto.request.UserSaveRequestDto;
import com.gimaletdinov.exampleProject.dto.request.UserUpdateRequestDto;
import com.gimaletdinov.exampleProject.dto.response.UserListResponseDto;
import com.gimaletdinov.exampleProject.model.Country;
import com.gimaletdinov.exampleProject.model.Document;
import com.gimaletdinov.exampleProject.model.DocumentType;
import com.gimaletdinov.exampleProject.model.Office;
import com.gimaletdinov.exampleProject.model.User;
import com.gimaletdinov.exampleProject.model.mapper.UserMapper;
import liquibase.pro.packaged.C;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.gimaletdinov.exampleProject.dao.DocumentTestHelper.getPopulateDocumentType;
import static com.gimaletdinov.exampleProject.dao.OfficeTestHelper.TEST_OFFICE_ID;
import static com.gimaletdinov.exampleProject.dao.OfficeTestHelper.getPopulateOffice;
import static com.gimaletdinov.exampleProject.dao.UserTestHelper.TEST_USER_ID;
import static com.gimaletdinov.exampleProject.dao.UserTestHelper.getPopulateCountry;
import static com.gimaletdinov.exampleProject.dao.UserTestHelper.getPopulateUser;
import static com.gimaletdinov.exampleProject.dao.UserTestHelper.getPopulateUserListRequestDto;
import static com.gimaletdinov.exampleProject.dao.UserTestHelper.getPopulateUserSaveRequestDto;
import static com.gimaletdinov.exampleProject.dao.UserTestHelper.getPopulateUserUpdateRequestDto;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
class UserServiceImplTest {

    private User newTestUser = getPopulateUser();

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private OfficeService officeService;

    @MockBean
    private CountryService countryService;

    @MockBean
    private DocumentTypeService documentTypeService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService = new UserServiceImpl(userRepository, officeService, countryService, documentTypeService, userMapper);

    @Test
    @Transactional
    void getAllUsersByPredicat() {
        List<User> testUsetList = new ArrayList<>();
        testUsetList.add(newTestUser);

        //Given
        UserListRequestDto userListRequestDto = getPopulateUserListRequestDto();
        when(userRepository.getAllUsersByPredicat(any())).thenReturn(testUsetList);

        //When
        List<UserListResponseDto> resultList = userService.getAllUsersByPredicat(userListRequestDto);

        //Then
        verify(userRepository).getAllUsersByPredicat(userMapper.toModel(userListRequestDto));
    }

    @Test
    @Transactional
    void getUserById() {
        getUserByIdFromRepository();
    }

    @Test
    @Transactional
    void updateUser() {
        //Given
        UserUpdateRequestDto userUpdateRequestDto = getPopulateUserUpdateRequestDto();
        Office newOffice = getPopulateOffice();
        Country newCountry = getPopulateCountry();
        User newUser = getPopulateUser();
        when(userRepository.getUserById(userUpdateRequestDto.getId())).thenReturn(newUser);
        when(officeService.getOfficeByIdFromRepository(userUpdateRequestDto.getOfficeId())).thenReturn(newOffice);
        when(countryService.getCountryById(userUpdateRequestDto.getCountryCode())).thenReturn(newCountry);

        //When
        userService.updateUser(userUpdateRequestDto);

        //Then
        userMapper.updateModel(userUpdateRequestDto, newUser);
        newUser.setCountry(newCountry);
        newUser.setOffice(newOffice);
        verify(userRepository).updateUser(newUser);

    }

    @Test
    @Transactional
    void saveUser() {
        UserSaveRequestDto userSaveRequestDto = getPopulateUserSaveRequestDto();
        Office newOffice = getPopulateOffice();
        Country newCountry = getPopulateCountry();
        DocumentType newDocumentType = getPopulateDocumentType();
        //Given
        when(officeService.getOfficeByIdFromRepository(userSaveRequestDto.getOfficeId())).thenReturn(newOffice);
        when(countryService.getCountryById(userSaveRequestDto.getCountryCode())).thenReturn(newCountry);
        when(documentTypeService.getDocumentTypeById(userSaveRequestDto.getDocCode())).thenReturn(newDocumentType);

        //When
        userService.saveUser(userSaveRequestDto);

        //Then
        User userInService = userMapper.toModel(userSaveRequestDto);
        userInService.setOffice(newOffice);
        userInService.setCountry(newCountry);
        Document document = userInService.getDocument();
        document.setDocumentType(newDocumentType);
        document.setUser(userInService);
        verify(userRepository).saveUser(userInService);
    }

    @Test
    @Transactional
    void getUserByIdFromRepository() {
        //Given
        when(userRepository.getUserById(TEST_USER_ID)).thenReturn(newTestUser);

        //When
        User userFromService = userService.getUserByIdFromRepository(TEST_USER_ID);

        //Then
        verify(userRepository).getUserById(TEST_USER_ID);
        assertNotNull(userFromService);
        assertEquals(userFromService.getId(), newTestUser.getId());
    }
}