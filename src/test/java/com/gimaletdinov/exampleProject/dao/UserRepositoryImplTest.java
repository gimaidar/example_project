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
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
class UserRepositoryImplTest {

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
    void getAllUsersByPredicat() {
        Office office = officeRepository.getOfficeById(1);

        User user = new User();
        user.setOffice(office);

        List<User> resultList = userRepository.getAllUsersByPredicat(user);
        assertTrue(resultList.size() > 0);
    }

    @Test
    @Transactional
    void getUserById() {
        User user = userRepository.getUserById(1);
        assertEquals(user.getId(), 1);
        assertNotNull(user);
    }

    @Test
    @Transactional
    void updateUser() {
        User user = userRepository.getUserById(1);
        user.setFirstName("update fname");
        user.setSecondName("update sname");
        user.setMiddleName("update mname");
        user.setPosition(1);
        user.setPhone("99999999999");
        user.setIsIdentified(false);

        userRepository.updateUser(user);
        User updatedUser = userRepository.getUserById(1);

        assertNotNull(updatedUser);
        assertEquals(user.getOffice(), updatedUser.getOffice());
        assertEquals(user.getFirstName(), updatedUser.getFirstName());
        assertEquals(user.getSecondName(), updatedUser.getSecondName());
        assertEquals(user.getMiddleName(), updatedUser.getMiddleName());
        assertEquals(user.getPosition(), updatedUser.getPosition());
        assertEquals(user.getPhone(), updatedUser.getPhone());
        assertEquals(user.getIsIdentified(), updatedUser.getIsIdentified());
    }

    @Test
    @Transactional
    void saveUser() {
        Office office = officeRepository.getOfficeById(1);
        Country country = countryRepository.getById(643);
        DocumentType documentType = documentTypeRepository.getById(21);
        Document document = new Document();
        document.setDocumentType(documentType);
        document.setNumber(111111);
        document.setDate(LocalDate.of(2022, 04, 12));


        User user = new User();
        document.setUser(user);
        user.setOffice(office);
        user.setDocument(document);
        user.setCountry(country);
        user.setFirstName("save fname");
        user.setSecondName("save sname");
        user.setMiddleName("save mname");
        user.setPosition(1);
        user.setPhone("99999999999");
        user.setIsIdentified(false);

        userRepository.saveUser(user);
        User savedUser = userRepository.getUserById(3);

        assertNotNull(savedUser);
        assertEquals(user.getOffice(), savedUser.getOffice());
        assertEquals(user.getCountry(), savedUser.getCountry());
        assertEquals(user.getDocument(), savedUser.getDocument());
        assertEquals(user.getFirstName(), savedUser.getFirstName());
        assertEquals(user.getSecondName(), savedUser.getSecondName());
        assertEquals(user.getMiddleName(), savedUser.getMiddleName());
        assertEquals(user.getPosition(), savedUser.getPosition());
        assertEquals(user.getPhone(), savedUser.getPhone());
        assertEquals(user.getIsIdentified(), savedUser.getIsIdentified());
    }
}