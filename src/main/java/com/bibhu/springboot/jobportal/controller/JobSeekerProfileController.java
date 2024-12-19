package com.bibhu.springboot.jobportal.controller;

import com.bibhu.springboot.jobportal.entity.JobSeekerProfile;
import com.bibhu.springboot.jobportal.entity.Skills;
import com.bibhu.springboot.jobportal.entity.Users;
import com.bibhu.springboot.jobportal.services.JobSeekerProfileService;
import com.bibhu.springboot.jobportal.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/job-seeker-profile")
public class JobSeekerProfileController {

    private final JobSeekerProfileService jobSeekerProfileService;
    private final UsersService usersService;

    @Autowired
    public JobSeekerProfileController(JobSeekerProfileService jobSeekerProfileService,
                                      UsersService usersService) {
        this.jobSeekerProfileService = jobSeekerProfileService;
        this.usersService = usersService;
    }

    @GetMapping("/")
    public String jobSeekerProfile(Model model) {
        JobSeekerProfile jobSeekerProfile = new JobSeekerProfile();
        List<Skills> skills = new ArrayList<>();
        Users user = usersService.getCurrentUser();
        Optional<JobSeekerProfile> seekerProfileOptional = jobSeekerProfileService.getJobSeekerProfile(user.getUserId());
        if (seekerProfileOptional.isPresent()) {
            jobSeekerProfile = seekerProfileOptional.get();
            if (jobSeekerProfile.getSkills().isEmpty()){
                skills.add(new Skills());
                jobSeekerProfile.setSkills(skills);
            }
        }
        model.addAttribute("skills", skills);
        model.addAttribute("profile", jobSeekerProfile);
        return "job-seeker-profile";
    }
}
