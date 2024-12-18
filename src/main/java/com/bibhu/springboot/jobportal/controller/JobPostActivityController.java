package com.bibhu.springboot.jobportal.controller;

import com.bibhu.springboot.jobportal.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import static com.bibhu.springboot.jobportal.util.AuthenticationUtil.getCurrentUsername;
import static com.bibhu.springboot.jobportal.util.AuthenticationUtil.isAnonymousAuthenticationTokenInstance;

@Controller
public class JobPostActivityController {

    private final UsersService usersService;

    @Autowired
    public JobPostActivityController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("/dashboard/")
    public String searchJobs(Model model) {
        Object currentUserProfile = usersService.getCurrentUserProfile();

        if (!isAnonymousAuthenticationTokenInstance()) {
            model.addAttribute("username", getCurrentUsername());
        }
        model.addAttribute("user", currentUserProfile);
        return "dashboard";
    }
}
