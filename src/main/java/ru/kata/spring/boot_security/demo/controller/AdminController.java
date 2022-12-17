package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleSevice;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final RoleSevice roleSevice;

    @Autowired
    public AdminController(UserService userService, RoleSevice roleSevice) {
        this.userService = userService;
        this.roleSevice = roleSevice;
    }

    @GetMapping
    public String showUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "/admin/admin";
    }

    @GetMapping("/{id}/update")
    public String getUpdate(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.get(id));
        model.addAttribute("roles", roleSevice.getRoles());
        return "/admin/update";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") int id) {
        userService.update(id, user);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        userService.remove(id);
        return "redirect:/admin";
    }
}
