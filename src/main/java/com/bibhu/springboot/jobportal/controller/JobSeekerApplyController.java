package com.bibhu.springboot.jobportal.controller;

import com.bibhu.springboot.jobportal.entity.JobPostActivity;
import com.bibhu.springboot.jobportal.entity.JobSeekerApply;
import com.bibhu.springboot.jobportal.entity.JobSeekerProfile;
import com.bibhu.springboot.jobportal.entity.JobSeekerSave;
import com.bibhu.springboot.jobportal.entity.RecruiterProfile;
import com.bibhu.springboot.jobportal.entity.Users;
import com.bibhu.springboot.jobportal.exception.CustomJobPortalException;
import com.bibhu.springboot.jobportal.services.JobPostActivityService;
import com.bibhu.springboot.jobportal.services.JobSeekerApplyService;
import com.bibhu.springboot.jobportal.services.JobSeekerProfileService;
import com.bibhu.springboot.jobportal.services.JobSeekerSaveService;
import com.bibhu.springboot.jobportal.services.RecruiterProfileService;
import com.bibhu.springboot.jobportal.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.bibhu.springboot.jobportal.util.AuthenticationUtil.isAnonymousAuthenticationTokenInstance;
import static com.bibhu.springboot.jobportal.util.AuthenticationUtil.isRecruiter;

@Controller
public class JobSeekerApplyController {

    private final JobPostActivityService jobPostActivityService;
    private final UsersService usersService;
    private final JobSeekerApplyService jobSeekerApplyService;
    private final JobSeekerSaveService jobSeekerSaveService;
    private final RecruiterProfileService recruiterProfileService;
    private final JobSeekerProfileService jobSeekerProfileService;

    @Autowired
    public JobSeekerApplyController(JobPostActivityService jobPostActivityService,
                                    UsersService usersService,
                                    JobSeekerApplyService jobSeekerApplyService,
                                    JobSeekerSaveService jobSeekerSaveService,
                                    RecruiterProfileService recruiterProfileService,
                                    JobSeekerProfileService jobSeekerProfileService) {
        this.jobPostActivityService = jobPostActivityService;
        this.usersService = usersService;
        this.jobSeekerApplyService = jobSeekerApplyService;
        this.jobSeekerSaveService = jobSeekerSaveService;
        this.recruiterProfileService = recruiterProfileService;
        this.jobSeekerProfileService = jobSeekerProfileService;
    }

    @GetMapping("job-details-apply/{id}")
    public String display(@PathVariable("id") int id,
                          Model model) {
        JobPostActivity jobDetails = jobPostActivityService.getJobDetails(id);
        List<JobSeekerApply> jobSeekerApplyList = jobSeekerApplyService.getJobCandidates(jobDetails);
        List<JobSeekerSave> jobSeekerSaveList = jobSeekerSaveService.getJobCandidates(jobDetails);
        if (!isAnonymousAuthenticationTokenInstance()) {
            if (isRecruiter()) {
                RecruiterProfile user = recruiterProfileService.getCurrentRecruiterProfile();
                if (null != user) {
                    model.addAttribute("applyList", jobSeekerApplyList);
                }
            } else {
                JobSeekerProfile user = jobSeekerProfileService.getCurrentJobSeekerProfile();
                if (null != user) {
                    boolean exists = false;
                    boolean saved = false;
                    for (JobSeekerApply jobSeekerApply : jobSeekerApplyList) {
                        if (Objects.equals(jobSeekerApply.getUserId().getUserAccountId(), user.getUserAccountId())) {
                            exists = true;
                            break;
                        }
                    }
                    for (JobSeekerSave jobSeekerSave : jobSeekerSaveList) {
                        if(Objects.equals(jobSeekerSave.getUserId().getUserAccountId(), user.getUserAccountId())) {
                            saved = true;
                            break;
                        }
                    }
                    model.addAttribute("alreadyApplied", exists);
                    model.addAttribute("alreadySaved", saved);
                }
            }
        }
        JobSeekerApply jobSeekerApply = new JobSeekerApply();
        model.addAttribute("applyJob", jobSeekerApply);
        model.addAttribute("jobDetails", jobDetails);
        model.addAttribute("user", usersService.getCurrentUserProfile());
        return "job-details";
    }

    @PostMapping("/job-details/apply/{id}")
    public String apply(@PathVariable("id") int id,
                        JobSeekerApply jobSeekerApply) {
        if (!isAnonymousAuthenticationTokenInstance()) {
            Users user = usersService.getCurrentUser();
            Optional<JobSeekerProfile> seekerProfile = jobSeekerProfileService.getJobSeekerProfile(user.getUserId());
            JobPostActivity jobPostActivity = jobPostActivityService.getJobDetails(id);
            if (seekerProfile.isPresent() && null != jobPostActivity) {
                jobSeekerApply = new JobSeekerApply();
                jobSeekerApply.setUserId(seekerProfile.get());
                jobSeekerApply.setJob(jobPostActivity);
                jobSeekerApply.setApplyDate(new Date());
            } else {
                throw new CustomJobPortalException("User not found");
            }
            jobSeekerApplyService.addNew(jobSeekerApply);
        }
        return "redirect:/dashboard/";
    }
}
