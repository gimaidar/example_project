package com.gimaletdinov.example_project.user.dao;

import com.gimaletdinov.example_project.user.model.User;

import java.util.List;

public interface UserDao {
    public List<User> getAll();
    public User getUser(int id);
    public void saveUser(User user);
    public void deleteUser(int id);
}
