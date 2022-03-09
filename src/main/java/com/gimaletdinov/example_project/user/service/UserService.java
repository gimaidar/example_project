package com.gimaletdinov.example_project.user.service;

import com.gimaletdinov.example_project.user.model.User;

import java.util.List;

public interface UserService {
    public List<User> getAll();
    public User getUser(int id);
    public void saveUser(User user);
    public void deleteUser(int id);
}
