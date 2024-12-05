package com.bibhu.springboot.jobportal.controller;

import com.bibhu.springboot.jobportal.entity.Users;
import com.bibhu.springboot.jobportal.entity.UsersType;
import com.bibhu.springboot.jobportal.services.UsersService;
import com.bibhu.springboot.jobportal.services.UsersTypeService;
import jakarta.validation.Valid;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UsersController {

    private final UsersTypeService usersTypeService;

    private final UsersService usersService;

    @Autowired
    public UsersController(UsersTypeService usersTypeService, UsersService usersService) {
        this.usersTypeService = usersTypeService;
        this.usersService = usersService;
    }

    @GetMapping("/register")
    public String register(Model model) {
        List<UsersType> usersTypes = usersTypeService.getAll();
        model.addAttribute("getAllTypes", usersTypes);
        model.addAttribute("user", new Users());
        return "register";
    }

    @PostMapping("/register/new")
    public String userRegister(@Valid Users user, Model model) {
        boolean emailIsUsed = usersService.getUserByEmail(user.getEmail());
        if(emailIsUsed){
            List<UsersType> usersTypes = usersTypeService.getAll();
            model.addAttribute("error", "Email already registered, try to login or register with other email.");
            model.addAttribute("getAllTypes", usersTypes);
            model.addAttribute("user", new Users());
            return "register";
        }
        usersService.addNewUser(user);
        return "dashboard";
    }

}
