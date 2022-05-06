package com.gimaletdinov.exampleProject.dao;

import com.gimaletdinov.exampleProject.model.Country;
import com.gimaletdinov.exampleProject.model.DocumentType;
import com.gimaletdinov.exampleProject.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.gimaletdinov.exampleProject.helper.CountryTestHelper.TEST_COUNTRY_CODE;
import static com.gimaletdinov.exampleProject.helper.CountryTestHelper.getPopulateCountry;
import static com.gimaletdinov.exampleProject.helper.DocumentTestHelper.TEST_DOCUMENT_TYPE_CODE;
import static com.gimaletdinov.exampleProject.helper.DocumentTestHelper.getPopulateDocumentType;
import static com.gimaletdinov.exampleProject.helper.UserTestHelper.TEST_USER_FIRST_NAME;
import static com.gimaletdinov.exampleProject.helper.UserTestHelper.TEST_USER_MIDDLE_NAME;
import static com.gimaletdinov.exampleProject.helper.UserTestHelper.TEST_USER_POSITION;
import static com.gimaletdinov.exampleProject.helper.UserTestHelper.TEST_USER_SECOND_NAME;
import static com.gimaletdinov.exampleProject.helper.UserTestHelper.getPopulateUser;
import static com.gimaletdinov.exampleProject.helper.UserTestHelper.getUserForUpdate;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
class UserRepositoryTest {

    private User testUserInBD;
    private Country testCountryInBD;
    private DocumentType testDocumentTypeInBD;

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
        testCountryInBD = countryRepository.save(getPopulateCountry());
        testDocumentTypeInBD = documentTypeRepository.save(getPopulateDocumentType());
        User newUser = getPopulateUser();
        newUser.getDocument().setDocumentType(testDocumentTypeInBD);
        newUser.setCountry(testCountryInBD);
        testUserInBD = userRepository.save(newUser);
    }

    @Test
    @Transactional
    @DisplayName("Тест: findAll(userSpecification()) (Найти пользователей по предикату)")
    void findAllUsersByPredicatByOfficeId() { //добавить с другими предикатами
        List<User> usersFromBD = userRepository.findAll(userSpecification());
        assertThat(usersFromBD).isNotEmpty();
        assertThat(usersFromBD).hasSize(1);
        assertThat(usersFromBD.get(0)).isEqualTo(testUserInBD);
    }

    @Test
    @Transactional
    @DisplayName("Тест: findById (Найти пользователя по id)")
    void findById() {
        User userFromBD = userRepository.findById(testUserInBD.getId()).get();
        assertThat(userFromBD).isNotNull();
        assertThat(userFromBD).isEqualTo(testUserInBD);
    }

    @Test
    @Transactional
    @DisplayName("Тест: updateUser (Обновить данные пользователя)")
    void updateUser() {
        User userForUpdate = getUserForUpdate(testUserInBD);

        User updatedUser = userRepository.save(userForUpdate);

        assertThat(updatedUser).isNotNull();
        assertThat(updatedUser).isEqualTo(userForUpdate);
    }

    @Test
    @Transactional
    @DisplayName("Тест: saveUser (Сохранить нового пользователя)")
    void saveUser() {
        User userForSave = getPopulateUser();
        userForSave.setCountry(testCountryInBD);
        userForSave.getDocument().setDocumentType(testDocumentTypeInBD);

        User savedUser = userRepository.save(userForSave);

        assertThat(savedUser).isNotNull();
        assertThat(savedUser).isEqualTo(userForSave);
    }

    private Specification<User> userSpecification() {
        return (userRoot, query, criteriaBuilder) -> criteriaBuilder.and(
                criteriaBuilder.equal(userRoot.get("office").get("id"), testUserInBD.getOffice().getId()),
                criteriaBuilder.equal(userRoot.get("firstName"), TEST_USER_FIRST_NAME),
                criteriaBuilder.equal(userRoot.get("secondName"), TEST_USER_SECOND_NAME),
                criteriaBuilder.equal(userRoot.get("middleName"), TEST_USER_MIDDLE_NAME),
                criteriaBuilder.equal(userRoot.get("position"), TEST_USER_POSITION),
                criteriaBuilder.equal(userRoot.get("country").get("code"), TEST_COUNTRY_CODE),
                criteriaBuilder.equal(userRoot.get("document").get("documentType").get("code"), TEST_DOCUMENT_TYPE_CODE));
    }
}