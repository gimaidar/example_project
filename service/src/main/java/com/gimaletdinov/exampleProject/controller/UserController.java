package com.gimaletdinov.exampleProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.gimaletdinov.exampleProject.dto.request.UserListRequestDto;
import com.gimaletdinov.exampleProject.dto.request.UserSaveRequestDto;
import com.gimaletdinov.exampleProject.dto.request.UserUpdateRequestDto;
import com.gimaletdinov.exampleProject.dto.response.ObjectSuccessResponseDto;
import com.gimaletdinov.exampleProject.dto.response.UserListResponseDto;
import com.gimaletdinov.exampleProject.dto.response.UserResponseDto;
import com.gimaletdinov.exampleProject.service.UserService;

import javax.validation.Valid;
import java.util.List;

/**
 *Контроллер для сущности Пользователь
 */
@RestController
@RequestMapping("api/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Метод возвращает список пользователей, найденных в БД по определенному предикату
     * @return список пользователей, найденных в БД по определенному придикату
     */
    @GetMapping("/list")
    public List<UserListResponseDto> getAllUsersByPredicat(@Valid @RequestBody UserListRequestDto userListRequestDto) {
        return userService.getAllUsersByPredicat(userListRequestDto);
    }

    @GetMapping("/{id}")
    public UserResponseDto getUserById(@PathVariable Integer id) {
        return userService.getUserById(id);
    }

    @PostMapping("/update")
    public ObjectSuccessResponseDto updateUser(@Valid @RequestBody UserUpdateRequestDto userUpdateRequestDto) {
        userService.updateUser(userUpdateRequestDto);
        return new ObjectSuccessResponseDto();
    }

    @PostMapping("/save")
    public ObjectSuccessResponseDto saveUser(@Valid @RequestBody UserSaveRequestDto userSaveRequestDto) {
        userService.saveUser(userSaveRequestDto);
        return new ObjectSuccessResponseDto();
    }
}
