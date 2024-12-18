package com.bibhu.springboot.jobportal.controller;

import com.bibhu.springboot.jobportal.entity.Users;
import com.bibhu.springboot.jobportal.entity.UsersType;
import com.bibhu.springboot.jobportal.services.UsersService;
import com.bibhu.springboot.jobportal.services.UsersTypeService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

import static com.bibhu.springboot.jobportal.util.AuthenticationUtil.getAuthentication;

@Controller
public class UsersController {

    private final UsersTypeService usersTypeService;
    private final UsersService usersService;

    @Autowired
    public UsersController(UsersTypeService usersTypeService,
                           UsersService usersService) {
        this.usersTypeService = usersTypeService;
        this.usersService = usersService;
    }

    @GetMapping("/register")
    public String register(Model model,
                           HttpServletRequest request) {
        String userType = request.getParameter("userType");
        List<UsersType> usersTypes = usersTypeService.getAll();
        model.addAttribute("getAllTypes", usersTypes);
        model.addAttribute("user", new Users());
        model.addAttribute("userType", userType);
        return "register";
    }

    @PostMapping("/register/new")
    public String userRegister(@Valid Users user,
                               Model model) {
        boolean emailIsUsed = usersService.checkForExistingEmailId(user.getEmail());
        if (emailIsUsed) {
            List<UsersType> usersTypes = usersTypeService.getAll();
            model.addAttribute("error", "Email already registered, try to login or register with other email.");
            model.addAttribute("getAllTypes", usersTypes);
            model.addAttribute("user", new Users());
            return "register";
        }
        usersService.addNewUser(user);
        return "redirect:/dashboard/";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request,
                         HttpServletResponse response) {
        Authentication authentication = getAuthentication();
        if (null != authentication) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        return "redirect:/";
    }

}
