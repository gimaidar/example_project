package com.gimaletdinov.example_project.controller.user;

import com.gimaletdinov.example_project.model.User;
import com.gimaletdinov.example_project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public List<User> getAllUsers(){
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable int id){
        User user = userService.getUser(id);
        if (user == null) {

        }
        return user;
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable int id){
        userService.deleteUser(id);
    }

    @PutMapping("/")
    public void saveUser(@RequestBody User user){
        userService.saveUser(user);
    }

    @PostMapping("/")
    public void addUser(@RequestBody User user){
        userService.saveUser(user);
    }

}
