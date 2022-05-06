package com.gimaletdinov.exampleProject.service;

import com.gimaletdinov.exampleProject.dao.UserRepository;
import com.gimaletdinov.exampleProject.dto.request.UserListRequestDto;
import com.gimaletdinov.exampleProject.dto.request.UserSaveRequestDto;
import com.gimaletdinov.exampleProject.dto.request.UserUpdateRequestDto;
import com.gimaletdinov.exampleProject.dto.response.UserListResponseDto;
import com.gimaletdinov.exampleProject.mapper.UserMapper;
import com.gimaletdinov.exampleProject.mapper.UserMapperImpl;
import com.gimaletdinov.exampleProject.model.Country;
import com.gimaletdinov.exampleProject.model.Document;
import com.gimaletdinov.exampleProject.model.DocumentType;
import com.gimaletdinov.exampleProject.model.Office;
import com.gimaletdinov.exampleProject.model.User;
import org.junit.jupiter.api.DisplayName;
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

import static com.gimaletdinov.exampleProject.helper.CountryDtoTestHelper.getPopulateCountry;
import static com.gimaletdinov.exampleProject.helper.DocumentDtoTestHelper.getPopulateDocumentType;
import static com.gimaletdinov.exampleProject.helper.OfficeDtoTestHelper.getPopulateOffice;
import static com.gimaletdinov.exampleProject.helper.UserDtoTestHelper.TEST_USER_ID;
import static com.gimaletdinov.exampleProject.helper.UserDtoTestHelper.getPopulateUser;
import static com.gimaletdinov.exampleProject.helper.UserDtoTestHelper.getPopulateUserListRequestDto;
import static com.gimaletdinov.exampleProject.helper.UserDtoTestHelper.getPopulateUserSaveRequestDto;
import static com.gimaletdinov.exampleProject.helper.UserDtoTestHelper.getPopulateUserUpdateRequestDto;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {UserServiceImpl.class, UserMapperImpl.class})
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
    private UserService userService;

    @Test
    @DisplayName("Тест: getAllUsersByPredicat (Найти пользователей по предикату)")
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

        assertThat(resultList).isNotEmpty();
        assertThat(resultList.get(0).getId()).isEqualTo(newTestUser.getId());
        assertThat(resultList.get(0).getFirstName()).isEqualTo(newTestUser.getFirstName());
        assertThat(resultList.get(0).getSecondName()).isEqualTo(newTestUser.getSecondName());
        assertThat(resultList.get(0).getMiddleName()).isEqualTo(newTestUser.getMiddleName());
        assertThat(resultList.get(0).getPosition()).isEqualTo(newTestUser.getPosition());
    }

    @Test
    @DisplayName("Тест: getUserById (Найти пользователя по id)")
    void getUserById() {
        getUserByIdFromRepository();
    }

    @Test
    @DisplayName("Тест: updateUser (Обновить данные пользователя)")
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
    @DisplayName("Тест: saveUser (Сохранить нового пользователя)")
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
    @DisplayName("Тест: getUserByIdFromRepository (Найти пользователя по id из репозитория)")
    void getUserByIdFromRepository() {
        //Given
        when(userRepository.findById(TEST_USER_ID)).thenReturn(Optional.ofNullable(newTestUser));

        //When
        User userFromService = userService.getUserByIdFromRepository(TEST_USER_ID);

        //Then
        verify(userRepository).findById(TEST_USER_ID);
        assertThat(userFromService).isNotNull();
        assertThat(userFromService).isEqualTo(newTestUser);
    }
}