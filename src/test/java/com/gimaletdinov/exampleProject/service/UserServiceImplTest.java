package com.gimaletdinov.exampleProject.service;

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
import com.gimaletdinov.exampleProject.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.gimaletdinov.exampleProject.Helper.DocumentTestHelper.getPopulateDocumentType;
import static com.gimaletdinov.exampleProject.Helper.OfficeTestHelper.getPopulateOffice;
import static com.gimaletdinov.exampleProject.Helper.UserTestHelper.TEST_USER_ID;
import static com.gimaletdinov.exampleProject.Helper.UserTestHelper.getPopulateCountry;
import static com.gimaletdinov.exampleProject.Helper.UserTestHelper.getPopulateUser;
import static com.gimaletdinov.exampleProject.Helper.UserTestHelper.getPopulateUserListRequestDto;
import static com.gimaletdinov.exampleProject.Helper.UserTestHelper.getPopulateUserSaveRequestDto;
import static com.gimaletdinov.exampleProject.Helper.UserTestHelper.getPopulateUserUpdateRequestDto;
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
        when(userRepository.findAll((Specification) any())).thenReturn(testUsetList);
        when(officeService.getOfficeByIdFromRepository(userListRequestDto.getOfficeId())).thenReturn(null);
        when(countryService.getCountryByCode(userListRequestDto.getCountryCode())).thenReturn(null);
        when(documentTypeService.getDocumentTypeByCode(userListRequestDto.getDocCode())).thenReturn(null);


        //When
        List<UserListResponseDto> resultList = userService.getAllUsersByPredicat(userListRequestDto);

        //Then
        verify(userRepository).findAll((Specification) any());
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
        when(userRepository.findById(userUpdateRequestDto.getId())).thenReturn(Optional.of(newUser));
        when(officeService.getOfficeByIdFromRepository(userUpdateRequestDto.getOfficeId())).thenReturn(newOffice);
        when(countryService.getCountryByCode(userUpdateRequestDto.getCountryCode())).thenReturn(newCountry);

        //When
        userService.updateUser(userUpdateRequestDto);

        //Then
        userMapper.updateModel(userUpdateRequestDto, newUser);
        newUser.setCountry(newCountry);
        newUser.setOffice(newOffice);
        verify(userRepository).save(newUser);

    }

    @Test
    @Transactional
    void saveUser() {
        //Given
        UserSaveRequestDto userSaveRequestDto = getPopulateUserSaveRequestDto();
        Office newOffice = getPopulateOffice();
        Country newCountry = getPopulateCountry();
        DocumentType newDocumentType = getPopulateDocumentType();

        when(officeService.getOfficeByIdFromRepository(userSaveRequestDto.getOfficeId())).thenReturn(newOffice);
        when(countryService.getCountryByCode(userSaveRequestDto.getCountryCode())).thenReturn(newCountry);
        when(documentTypeService.getDocumentTypeByCode(userSaveRequestDto.getDocCode())).thenReturn(newDocumentType);

        User userInService = userMapper.toModel(userSaveRequestDto);
        userInService.setOffice(newOffice);
        userInService.setCountry(newCountry);
        Document document = userInService.getDocument();
        document.setDocumentType(newDocumentType);
        document.setUser(userInService);
        when(userRepository.save(userInService)).thenReturn(userInService);

        //When
        userService.saveUser(userSaveRequestDto);

        //Then
        verify(userRepository).save(userInService);
    }

    @Test
    @Transactional
    void getUserByIdFromRepository() {
        //Given
        when(userRepository.findById(TEST_USER_ID)).thenReturn(Optional.ofNullable(newTestUser));

        //When
        User userFromService = userService.getUserByIdFromRepository(TEST_USER_ID);

        //Then
        verify(userRepository).findById(TEST_USER_ID);
        assertNotNull(userFromService);
        assertEquals(userFromService.getId(), newTestUser.getId());
    }
}