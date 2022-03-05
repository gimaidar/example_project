package com.gimaletdinov.example_project.dao;

import com.gimaletdinov.example_project.model.User;

import java.util.List;

public interface UserDao {
    public List<User> getAll();
    public User getUser(int id);
    public void saveUser(User user);
    public void deleteUser(int id);
}
