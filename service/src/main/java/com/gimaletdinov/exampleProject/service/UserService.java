package com.gimaletdinov.exampleProject.service;

import com.gimaletdinov.exampleProject.dto.request.UserListRequestDto;
import com.gimaletdinov.exampleProject.dto.request.UserSaveRequestDto;
import com.gimaletdinov.exampleProject.dto.request.UserUpdateRequestDto;
import com.gimaletdinov.exampleProject.dto.response.UserListResponseDto;
import com.gimaletdinov.exampleProject.dto.response.UserResponseDto;
import com.gimaletdinov.exampleProject.model.User;

import java.util.List;

/**
 * Интерфейс сервиса для сущности Пользователь
 */
public interface UserService {

    /**
     * Метод возвращает список пользователей, найденных в БД по определенному предикату
     * @return список пользователей, найденных в БД по определенному придикату
     */
    List<UserListResponseDto> getAllUsersByPredicat(UserListRequestDto userListRequestDto);

    /**
     * Метод возвращает пользователя по id и преобразует в формат ответа
     * @param id
     * @return пользователь
     */
    UserResponseDto getUserById(int id);

    /**
     * Метод для обновления данных пользователя
     * @param userUpdateRequestDto
     */
    void updateUser(UserUpdateRequestDto userUpdateRequestDto);

    /**
     * Метод для сохранения нового пользователя
     * @param userSaveRequestDto
     */
    void saveUser(UserSaveRequestDto userSaveRequestDto);

    /**
     * Метод возвращает пользователя по id
     * @param id
     * @return пользователь
     */
    User getUserByIdFromRepository(int id);
}
