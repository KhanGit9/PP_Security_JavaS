package ru.kata.spring.boot_security.demo;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.annotation.PostConstruct;
import java.util.Set;

@Component
public class InitUsersAndRoles {

    private final UserService userService;
    private final RoleService roleService;

    public InitUsersAndRoles(UserService userRepository, RoleService roleRepository) {
        this.userService = userRepository;
        this.roleService = roleRepository;
    }

    @PostConstruct
    public void checkUsersTable() {
        if(userService.findAll().isEmpty()) {
            createUsers();
        }
    }

    @Transactional
    public void createUsers() {
        Role user = new Role("ROLE_USER");
        Role admin = new Role("ROLE_ADMIN");

        roleService.save(user);
        roleService.save(admin);

        Set<Role> roles = Set.of(user, admin);

        User user1 = new User("2@2", "2");

        User user2 = new User("1@1","1");

        User user3 = new User("ramz@mail.ru", "123");

        user1.setRoles(roles);
        user2.setRoles(roles);
        user3.setRoles(roles);

        user3.setFirstName("Mit1ok");
        user3.setLastName("Usman");
        user3.setAge(40);

        user1.setFirstName("Khasan");
        user1.setLastName("Zandac");
        user1.setAge(35);

        user2.setFirstName("Usman");
        user2.setLastName("Kardan");
        user2.setAge(14);

        userService.saveUser(user1);
        userService.saveUser(user2);
        userService.saveUser(user3);

    }
}

