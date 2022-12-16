package ru.kata.spring.boot_security.demo.reposytory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.Role;

@Repository
public interface RoleReposytory extends JpaRepository<Role, Integer> {

}
