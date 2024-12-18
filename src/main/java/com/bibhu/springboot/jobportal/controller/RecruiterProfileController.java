package com.bibhu.springboot.jobportal.controller;

import com.bibhu.springboot.jobportal.entity.RecruiterProfile;
import com.bibhu.springboot.jobportal.entity.Users;
import com.bibhu.springboot.jobportal.services.RecruiterProfileService;
import com.bibhu.springboot.jobportal.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/recruiter-profile")
public class RecruiterProfileController {

    private final UsersService usersService;
    private final RecruiterProfileService recruiterProfileService;

    @Autowired
    public RecruiterProfileController(UsersService usersService,
                                      RecruiterProfileService recruiterProfileService) {
        this.usersService = usersService;
        this.recruiterProfileService = recruiterProfileService;
    }

    @GetMapping("/")
    public String recruiterProfile(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof AnonymousAuthenticationToken) {
            String currentUserName = authentication.getName();
            Users users = usersService.findUserByEmail(currentUserName)
                    .orElseThrow(() -> new UsernameNotFoundException("Could not found user " + currentUserName));
            Optional<RecruiterProfile> recruiterProfileOptional = recruiterProfileService.findUserProfile(users.getUserId());
            recruiterProfileOptional.ifPresent(recruiterProfile -> model.addAttribute("profile", recruiterProfile));
        }
        return "recruiter_profile";
    }

}
