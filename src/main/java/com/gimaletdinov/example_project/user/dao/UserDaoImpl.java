package com.gimaletdinov.example_project.user.dao;

import com.gimaletdinov.example_project.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

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
