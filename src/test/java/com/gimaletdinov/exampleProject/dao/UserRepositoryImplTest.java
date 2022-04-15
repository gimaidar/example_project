package com.gimaletdinov.exampleProject.dao;

import com.gimaletdinov.exampleProject.controller.handler.exceptions.NoSuchObjectException;
import com.gimaletdinov.exampleProject.dto.request.UserListRequestDto;
import com.gimaletdinov.exampleProject.dto.request.UserSaveRequestDto;
import com.gimaletdinov.exampleProject.dto.request.UserUpdateRequestDto;
import com.gimaletdinov.exampleProject.dto.response.UserListResponseDto;
import com.gimaletdinov.exampleProject.model.Country;
import com.gimaletdinov.exampleProject.model.Document;
import com.gimaletdinov.exampleProject.model.DocumentType;
import com.gimaletdinov.exampleProject.model.Office;
import com.gimaletdinov.exampleProject.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static com.gimaletdinov.exampleProject.dao.DocumentTestHelper.TEST_DOCUMENT_TYPE_ID;
import static com.gimaletdinov.exampleProject.dao.DocumentTestHelper.getPopulateDocument;
import static com.gimaletdinov.exampleProject.dao.OfficeTestHelper.TEST_OFFICE_ID;
import static com.gimaletdinov.exampleProject.dao.OfficeTestHelper.assertOfficesEquals;
import static com.gimaletdinov.exampleProject.dao.UserTestHelper.TEST_COUNTRY_ID;
import static com.gimaletdinov.exampleProject.dao.UserTestHelper.TEST_USER_ID;
import static com.gimaletdinov.exampleProject.dao.UserTestHelper.assertUsersEquals;
import static com.gimaletdinov.exampleProject.dao.UserTestHelper.getPopulateUser;
import static com.gimaletdinov.exampleProject.dao.UserTestHelper.getUserForUpdate;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;

@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
class UserRepositoryImplTest {

    private User newTestUser = getPopulateUser();

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OfficeRepository officeRepository;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private DocumentTypeRepository documentTypeRepository;

    @Test
    @Transactional
    void getAllUsersByPredicatByOfficeId() { //добавить с другими предикатами
        Office office = officeRepository.getOfficeById(TEST_OFFICE_ID);

        User newUser = new User();
        newUser.setOffice(office);

        List<User> usersFromBD = userRepository.getAllUsersByPredicat(newUser);
        assertFalse(usersFromBD.isEmpty());
        assertTrue(usersFromBD.size() == 1);
        assertOfficesEquals(office, usersFromBD.get(0).getOffice());
    }

    @Test
    @Transactional
    void getUserById() {
        User userFromBD = userRepository.getUserById(TEST_USER_ID);
        assertNotNull(userFromBD);
        assertUsersEquals(newTestUser, userFromBD);
    }

    @Test
    @Transactional
    void updateUser() {
        User user = userRepository.getUserById(TEST_USER_ID);
        User userForUpdate = getUserForUpdate(user);

        userRepository.updateUser(userForUpdate);
        User updatedUser = userRepository.getUserById(TEST_USER_ID  );

        assertNotNull(updatedUser);
        assertUsersEquals(userForUpdate, updatedUser);
    }

    @Test
    @Transactional
    void saveUser() {
        Office office = officeRepository.getOfficeById(TEST_OFFICE_ID);

        Country country = countryRepository.getById(TEST_COUNTRY_ID);
        DocumentType documentType = documentTypeRepository.getById(TEST_DOCUMENT_TYPE_ID);

        Document document = getPopulateDocument();
        document.setDocumentType(documentType);

        User newUser = getPopulateUser();
        document.setUser(newUser);
        newUser.setOffice(office);
        newUser.setDocument(document);
        newUser.setCountry(country);

        userRepository.saveUser(newUser);
        User savedUser = userRepository.getUserById(TEST_USER_ID + 1);

        assertNotNull(savedUser);
        assertUsersEquals(newUser, savedUser);
    }
}