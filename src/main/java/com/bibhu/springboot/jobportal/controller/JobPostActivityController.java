package com.bibhu.springboot.jobportal.controller;

import com.bibhu.springboot.jobportal.entity.JobPostActivity;
import com.bibhu.springboot.jobportal.entity.RecruiterJobsDTO;
import com.bibhu.springboot.jobportal.entity.RecruiterProfile;
import com.bibhu.springboot.jobportal.entity.Users;
import com.bibhu.springboot.jobportal.services.JobPostActivityService;
import com.bibhu.springboot.jobportal.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;
import java.util.List;

import static com.bibhu.springboot.jobportal.util.AuthenticationUtil.getCurrentUsername;
import static com.bibhu.springboot.jobportal.util.AuthenticationUtil.isAnonymousAuthenticationTokenInstance;
import static com.bibhu.springboot.jobportal.util.AuthenticationUtil.isRecruiter;

@Controller
public class JobPostActivityController {

    private final UsersService usersService;
    private final JobPostActivityService jobPostActivityService;

    @Autowired
    public JobPostActivityController(UsersService usersService,
                                     JobPostActivityService jobPostActivityService) {
        this.usersService = usersService;
        this.jobPostActivityService = jobPostActivityService;
    }

    @GetMapping("/dashboard/")
    public String searchJobs(Model model) {
        Object currentUserProfile = usersService.getCurrentUserProfile();
        if (!isAnonymousAuthenticationTokenInstance()) {
            model.addAttribute("username", getCurrentUsername());
            if (isRecruiter()) {
                List<RecruiterJobsDTO> recruiterJobs =
                        jobPostActivityService.getRecruiterJobs(((RecruiterProfile) currentUserProfile).getUserAccountId());
                model.addAttribute("jobPost", recruiterJobs);
            }
        }
        model.addAttribute("user", currentUserProfile);
        return "dashboard";
    }

    @GetMapping("/dashboard/add")
    public String addJobs(Model model) {
        model.addAttribute("jobPostActivity", new JobPostActivity());
        model.addAttribute("user", usersService.getCurrentUserProfile());
        return "add-jobs";
    }

    @PostMapping("/dashboard/addNew")
    public String addNewJobs (JobPostActivity jobPostActivity,
                              Model model) {
        Users user = usersService.getCurrentUser();
        if (null != user) {
            jobPostActivity.setPostedById(user);
        }
        jobPostActivity.setPostedDate(new Date());
        model.addAttribute("jobPostActivity", jobPostActivity);
        JobPostActivity savedJobPostActivity = jobPostActivityService.saveJobPost(jobPostActivity);
        return "redirect:/dashboard/";
    }

    @PostMapping("dashboard/edit/{id}")
    public String editJob(@PathVariable("id") int id,
                          Model model) {
        JobPostActivity jobDetails = jobPostActivityService.getJobDetails(id);
        model.addAttribute("jobPostActivity", jobDetails);
        model.addAttribute("user", usersService.getCurrentUserProfile());
        return "add-jobs";
    }

}
