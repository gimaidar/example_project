package com.gimaletdinov.exampleProject.dao;

import com.gimaletdinov.exampleProject.dto.request.UserListRequestDto;
import com.gimaletdinov.exampleProject.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.gimaletdinov.exampleProject.Helper.UserTestHelper.getPopulateUserListRequestDto;
import static com.gimaletdinov.exampleProject.dao.UserSpecification.userSpecification;
import static com.gimaletdinov.exampleProject.Helper.UserTestHelper.assertUsersEquals;
import static com.gimaletdinov.exampleProject.Helper.UserTestHelper.getPopulateUser;
import static com.gimaletdinov.exampleProject.Helper.UserTestHelper.getUserForUpdate;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
class UserRepositoryImplTest {

    private User testUserInBD;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OfficeRepository officeRepository;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private DocumentTypeRepository documentTypeRepository;

    @BeforeEach
    void saveTestUserInBD(){
        testUserInBD = userRepository.save(getPopulateUser());
    }

    @Test
    @Transactional
    void getAllUsersByPredicatByOfficeId() { //добавить с другими предикатами
        UserListRequestDto userListRequestDto = getPopulateUserListRequestDto();
        List<User> usersFromBD = userRepository.findAll(userSpecification(userListRequestDto));
        assertFalse(usersFromBD.isEmpty());
        assertTrue(usersFromBD.size() == 1);
        assertUsersEquals(testUserInBD, usersFromBD.get(0));
    }

    @Test
    @Transactional
    void getUserById() {
        User userFromBD = userRepository.findById(testUserInBD.getId()).get();
        assertNotNull(userFromBD);
        assertUsersEquals(testUserInBD, userFromBD);
    }

    @Test
    @Transactional
    void updateUser() {
        User userForUpdate = getUserForUpdate(testUserInBD);

        User updatedUser = userRepository.save(userForUpdate);

        assertNotNull(updatedUser);
        assertUsersEquals(userForUpdate, updatedUser);
    }

    @Test
    @Transactional
    void saveUser() {

        User userForSave = getPopulateUser();

        User savedUser = userRepository.save(userForSave);

        assertNotNull(savedUser);
        assertUsersEquals(userForSave, savedUser);
    }
}