package com.gimaletdinov.exampleProject.service;

import com.gimaletdinov.exampleProject.controller.handler.exceptions.NoSuchObjectException;
import com.gimaletdinov.exampleProject.dto.request.UserListRequestDto;
import com.gimaletdinov.exampleProject.dto.request.UserSaveRequestDto;
import com.gimaletdinov.exampleProject.dto.request.UserUpdateRequestDto;
import com.gimaletdinov.exampleProject.dto.response.UserListResponseDto;
import com.gimaletdinov.exampleProject.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @Test
    @Transactional
    void getAllUsersByPredicat() {
        UserListRequestDto requestDto = new UserListRequestDto();
        requestDto.setOfficeId(1);

        List<UserListResponseDto> resultList = userService.getAllUsersByPredicat(requestDto);
        assertTrue(resultList.size() > 0);

        requestDto.setOfficeId(1000);
        assertThrows(NoSuchObjectException.class, () -> userService.getAllUsersByPredicat(requestDto));
    }

    @Test
    @Transactional
    void getUserById() {
        this.getUserByIdFromRepository();
    }

    @Test
    @Transactional
    void updateUser() {
        UserUpdateRequestDto requestDto = new UserUpdateRequestDto();
        requestDto.setId(1);
        requestDto.setOfficeId(1);
        requestDto.setFirstName("update fname");
        requestDto.setSecondName("update sname");
        requestDto.setMiddleName("update mname");
        requestDto.setPosition(1);
        requestDto.setPhone("99999999999");
        requestDto.setIsIdentified(false);
        requestDto.setDocNumber(111111);
        requestDto.setDocDate(LocalDate.of(2022, 04, 12));
        requestDto.setCountryCode(643);

        userService.updateUser(requestDto);
        User updatedUser = userService.getUserByIdFromRepository(1);

        assertNotNull(updatedUser);
        assertEquals(requestDto.getOfficeId(), updatedUser.getOffice().getId());
        assertEquals(requestDto.getFirstName(), updatedUser.getFirstName());
        assertEquals(requestDto.getSecondName(), updatedUser.getSecondName());
        assertEquals(requestDto.getMiddleName(), updatedUser.getMiddleName());
        assertEquals(requestDto.getPosition(), updatedUser.getPosition());
        assertEquals(requestDto.getPhone(), updatedUser.getPhone());
        assertEquals(requestDto.getIsIdentified(), updatedUser.getIsIdentified());
        assertEquals(requestDto.getDocNumber(), updatedUser.getDocument().getNumber());
        assertEquals(requestDto.getDocDate(), updatedUser.getDocument().getDate());
        assertEquals(requestDto.getCountryCode(), updatedUser.getCountry().getId());
    }

    @Test
    @Transactional
    void saveUser() {
        UserSaveRequestDto requestDto = new UserSaveRequestDto();
        requestDto.setOfficeId(1);
        requestDto.setFirstName("save fname");
        requestDto.setSecondName("save sname");
        requestDto.setMiddleName("save mname");
        requestDto.setPosition(1);
        requestDto.setPhone("99999999999");
        requestDto.setIsIdentified(false);
        requestDto.setDocCode(21);
        requestDto.setDocNumber(111111);
        requestDto.setDocDate(LocalDate.of(2022, 04, 12));
        requestDto.setCountryCode(643);

        assertThrows(NoSuchObjectException.class, () -> userService.getUserByIdFromRepository(3));

        userService.saveUser(requestDto);
        User savedUser = userService.getUserByIdFromRepository(3);

        assertNotNull(savedUser);
        assertEquals(requestDto.getOfficeId(), savedUser.getOffice().getId());
        assertEquals(requestDto.getFirstName(), savedUser.getFirstName());
        assertEquals(requestDto.getSecondName(), savedUser.getSecondName());
        assertEquals(requestDto.getMiddleName(), savedUser.getMiddleName());
        assertEquals(requestDto.getPosition(), savedUser.getPosition());
        assertEquals(requestDto.getPhone(), savedUser.getPhone());
        assertEquals(requestDto.getIsIdentified(), savedUser.getIsIdentified());
        assertEquals(requestDto.getDocCode(), savedUser.getDocument().getDocumentType().getId());
        assertEquals(requestDto.getDocNumber(), savedUser.getDocument().getNumber());
        assertEquals(requestDto.getDocDate(), savedUser.getDocument().getDate());
        assertEquals(requestDto.getCountryCode(), savedUser.getCountry().getId());
    }

    @Test
    @Transactional
    void getUserByIdFromRepository() {
        User user = userService.getUserByIdFromRepository(1);
        assertEquals(user.getId(), 1);
        assertNotNull(user);
    }
}