package com.bibhu.springboot.jobportal.controller;

import com.bibhu.springboot.jobportal.entity.JobPostActivity;
import com.bibhu.springboot.jobportal.entity.JobSeekerProfile;
import com.bibhu.springboot.jobportal.entity.JobSeekerSave;
import com.bibhu.springboot.jobportal.entity.Users;
import com.bibhu.springboot.jobportal.exception.CustomJobPortalException;
import com.bibhu.springboot.jobportal.services.JobPostActivityService;
import com.bibhu.springboot.jobportal.services.JobSeekerProfileService;
import com.bibhu.springboot.jobportal.services.JobSeekerSaveService;
import com.bibhu.springboot.jobportal.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.bibhu.springboot.jobportal.util.AuthenticationUtil.isAnonymousAuthenticationTokenInstance;

@Controller
public class JobSeekerSaveController {

    private final UsersService usersService;
    private final JobSeekerProfileService jobSeekerProfileService;
    private final JobPostActivityService jobPostActivityService;
    private final JobSeekerSaveService jobSeekerSaveService;

    @Autowired
    public JobSeekerSaveController(UsersService usersService,
                                   JobSeekerProfileService jobSeekerProfileService,
                                   JobPostActivityService jobPostActivityService,
                                   JobSeekerSaveService jobSeekerSaveService) {
        this.usersService = usersService;
        this.jobSeekerProfileService = jobSeekerProfileService;
        this.jobPostActivityService = jobPostActivityService;
        this.jobSeekerSaveService = jobSeekerSaveService;
    }

    @PostMapping("job-details/save/{id}")
    public String save(@PathVariable("id") int id,
                       JobSeekerSave jobSeekerSave) {
        if (!isAnonymousAuthenticationTokenInstance()) {
            Users user = usersService.getCurrentUser();
            Optional<JobSeekerProfile> seekerProfile = jobSeekerProfileService.getJobSeekerProfile(user.getUserId());
            JobPostActivity jobPostActivity = jobPostActivityService.getJobDetails(id);
            if (seekerProfile.isPresent() && null != jobPostActivity) {
                jobSeekerSave = new JobSeekerSave();
                jobSeekerSave.setUserId(seekerProfile.get());
                jobSeekerSave.setJob(jobPostActivity);
            } else {
                throw new CustomJobPortalException("User not found");
            }
            jobSeekerSaveService.addNew(jobSeekerSave);
        }
        return "redirect:/dashboard/";
    }

    @GetMapping("saved-jobs/")
    public String savedJob(Model model) {
        List<JobPostActivity> jobPost = new ArrayList<>();
        Object currentUserProfile = usersService.getCurrentUser();
        List<JobSeekerSave> jobSeekerSaveList = jobSeekerSaveService.getCandidatesJob((JobSeekerProfile) currentUserProfile);
        for (JobSeekerSave jobSeekerSave : jobSeekerSaveList) {
            jobPost.add(jobSeekerSave.getJob());
        }
        model.addAttribute("jobPost", jobPost);
        model.addAttribute("user", currentUserProfile);
        return "saved-jobs";
    }
}
