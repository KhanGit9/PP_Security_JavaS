package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleSevice;
import ru.kata.spring.boot_security.demo.service.UserService;

@Controller
public class AllSeeController {
	private final RoleSevice roleSevice;
	private final UserService userService;
	@Autowired
	public AllSeeController(RoleSevice roleSevice, UserService userService) {
		this.roleSevice = roleSevice;
		this.userService = userService;
	}

	@GetMapping (value = "/login")
	public String getLogin() {
		return "login";
	}

	@GetMapping(value = "/add")
	public String addUser (Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("roles", roleSevice.getRoles());
		return "/add";
	}

	@PostMapping(value = "/user")
	public String saveNewUser(@ModelAttribute("user") User user) {
		userService.add(user);
		return "user/user";
	}

}