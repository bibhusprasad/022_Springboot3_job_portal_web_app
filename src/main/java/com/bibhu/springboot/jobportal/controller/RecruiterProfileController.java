package com.bibhu.springboot.jobportal.controller;

import com.bibhu.springboot.jobportal.entity.RecruiterProfile;
import com.bibhu.springboot.jobportal.entity.Users;
import com.bibhu.springboot.jobportal.repository.UsersRepository;
import com.bibhu.springboot.jobportal.services.RecruiterProfileService;
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

    private final UsersRepository usersRepository;
    private final RecruiterProfileService recruiterProfileService;

    @Autowired
    public RecruiterProfileController(UsersRepository usersRepository,
                                      RecruiterProfileService recruiterProfileService) {
        this.usersRepository = usersRepository;
        this.recruiterProfileService = recruiterProfileService;
    }

    @GetMapping("/")
    public String recruiterProfile(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication instanceof AnonymousAuthenticationToken) {
            String currentUserName = authentication.getName();
            Users users = usersRepository.findByEmail(currentUserName)
                    .orElseThrow(() -> new UsernameNotFoundException("Could not found user " + currentUserName));
            Optional<RecruiterProfile> recruiterProfileOptional = recruiterProfileService.getOne(users.getUserId());
            recruiterProfileOptional.ifPresent(recruiterProfile -> model.addAttribute("profile", recruiterProfile));
        }
        return "recruiter_profile";
    }

}
