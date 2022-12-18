package ru.kata.spring.boot_security.demo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.reposytory.UserReposytory;


import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    private final PasswordEncoder passwordEncoder;
    private final UserReposytory userReposytory;
    @Autowired
    public UserServiceImpl(@Lazy PasswordEncoder passwordEncoder, UserReposytory userReposytory) {
        this.passwordEncoder = passwordEncoder;
        this.userReposytory = userReposytory;
    }

    @Transactional
    @Override
    public void add(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userReposytory.save(user);
    }
    @Transactional
    @Override
    public void remove(int id) {
        userReposytory.deleteById(id);
    }

    @Transactional
    @Override
    public void update(int id, User user) {
        user.setId(id);
        userReposytory.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userReposytory.findAll();
    }

    @Override
    public User get(int id) {
        return userReposytory.findById(id).get();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userReposytory.findByName(username);
        if (user.isEmpty())
            throw new UsernameNotFoundException("User not found!");
        return  user.get();
    }
}
