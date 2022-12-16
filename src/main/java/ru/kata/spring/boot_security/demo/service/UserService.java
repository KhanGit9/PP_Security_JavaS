package ru.kata.spring.boot_security.demo.service;


import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;


import java.util.List;
import java.util.Optional;

public interface UserService {
    void add(User user);
    void remove(int id);
    void update(int id, User user);
    List<User> getAllUsers();
    public User get(int id);
}
