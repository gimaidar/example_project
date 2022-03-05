package com.gimaletdinov.example_project.service;

import com.gimaletdinov.example_project.model.User;

import java.util.List;

public interface UserService {
    public List<User> getAll();
    public User getUser(int id);
    public void saveUser(User user);
    public void deleteUser(int id);
}
