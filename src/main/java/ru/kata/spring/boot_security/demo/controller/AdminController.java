package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleSevice;
import ru.kata.spring.boot_security.demo.service.UserService;

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
    public String showUsers(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("oneUser", userService.loadUserByUsername(user.getUsername()));
        model.addAttribute("roles", roleSevice.getRoles());
        model.addAttribute("newUser", new User());
        return "/admin/admin";
    }

    @PatchMapping("/update{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") int id) {
        userService.update(id, user);
        return "redirect:/admin";
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        userService.remove(id);
        return "redirect:/admin";
    }
}
