package com.gimaletdinov.example_project.user.service;

import com.gimaletdinov.example_project.user.dao.UserDao;
import com.gimaletdinov.example_project.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public User getUser(int id) {
        return null;
    }

    @Override
    public void saveUser(User user) {

    }

    @Override
    public void deleteUser(int id) {

    }
}
