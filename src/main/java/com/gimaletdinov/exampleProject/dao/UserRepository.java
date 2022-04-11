package com.gimaletdinov.exampleProject.dao;

import com.gimaletdinov.exampleProject.model.User;
import java.util.List;

/**
 * Интерфейс репозитория для сущности Пользователь
 */
public interface UserRepository {

    /**
     * Метод возвращает список пользователей, найденных в БД по определенному предикату
     * @return список пользователей, найденных в БД по определенному придикату
     */
    List<User> getAllUsersByPredicat(User user);

    /**
     * Метод возвращает пользователя по id
     * @param id
     * @return пользователь
     */
    User getUserById(int id);

    /**
     * Метод для обновления данных пользователя
     * @param user
     */
    void updateUser(User user);

    /**
     * Метод для сохранения нового пользователя
     * @param user
     */
    void saveUser(User user);
}
