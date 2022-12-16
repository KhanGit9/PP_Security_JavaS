package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.reposytory.RoleReposytory;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleSevice {
    RoleReposytory roleReposytory;

    @Autowired
    public RoleServiceImpl(RoleReposytory roleReposytory) {
        this.roleReposytory = roleReposytory;
    }
    @Override
    public Role getRoleByID(int id) {
        Optional role = roleReposytory.findById(id);
        return (Role) role.get();
    }

    @Override
    public List<Role> getRoles() {
        return roleReposytory.findAll();
    }


}
