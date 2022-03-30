package com.gimaletdinov.exampleProject.dao;

import com.gimaletdinov.exampleProject.model.User;
import java.util.List;

public interface UserRepository {

    List<User> getAllUsersByPredicat(User user);

    User getUserById(int id);

    void updateUser(User user);

    void saveUser(User user);
}
