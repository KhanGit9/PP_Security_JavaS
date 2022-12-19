package ru.kata.spring.boot_security.demo.reposytory;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserReposytory extends JpaRepository<User, Integer> {
    @EntityGraph(value = "User.roles")
    Optional<User> findByName(String name);
    List<User> findAll();
}
