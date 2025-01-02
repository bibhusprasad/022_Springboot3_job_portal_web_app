package com.bibhu.springboot.jobportal.controller;

import com.bibhu.springboot.jobportal.entity.JobSeekerProfile;
import com.bibhu.springboot.jobportal.entity.Skills;
import com.bibhu.springboot.jobportal.entity.Users;
import com.bibhu.springboot.jobportal.exception.CustomJobPortalException;
import com.bibhu.springboot.jobportal.services.JobSeekerProfileService;
import com.bibhu.springboot.jobportal.services.UsersService;
import com.bibhu.springboot.jobportal.util.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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

    @PostMapping("/addNew")
    public String addNewJobSeeker(JobSeekerProfile jobSeekerProfile,
                                  Model model,
                                  @RequestParam("image")MultipartFile image,
                                  @RequestParam("pdf")MultipartFile pdf) {
        Users user = usersService.getCurrentUser();
        jobSeekerProfile.setUserId(user);
        jobSeekerProfile.setUserAccountId(user.getUserId());
        List<Skills> skillsList = new ArrayList<>();
        model.addAttribute("skills", skillsList);
        model.addAttribute("profile", jobSeekerProfile);

        for (Skills skills : jobSeekerProfile.getSkills()) {
            skills.setJobSeekerProfile(jobSeekerProfile);
        }

        String imageName = "";
        String resumeName = "";
        if (!Objects.equals(image.getOriginalFilename(), "")) {
            imageName = StringUtils.cleanPath(Objects.requireNonNull(image.getOriginalFilename()));
            jobSeekerProfile.setProfilePhoto(imageName);
        }
        if (!Objects.equals(pdf.getOriginalFilename(), "")) {
            resumeName = StringUtils.cleanPath(Objects.requireNonNull(pdf.getOriginalFilename()));
            jobSeekerProfile.setResume(resumeName);
        }
        JobSeekerProfile seekerProfile = jobSeekerProfileService.addNewJobSeeker(jobSeekerProfile);

        try {
            String uploadDir = "photos/candidate/" + jobSeekerProfile.getUserAccountId();
            if (!Objects.equals(image.getOriginalFilename(), "")) {
                FileUploadUtil.saveFile(uploadDir, imageName, image);
            }
            if (!Objects.equals(pdf.getOriginalFilename(), "")) {
                FileUploadUtil.saveFile(uploadDir, resumeName, pdf);
            }
        } catch (Exception e) {
            throw new CustomJobPortalException("Unable to upload image or resume", e);
        }
        return "redirect:/dashboard/";
    }

    @GetMapping("/{id}")
    public String candidateProfile(@PathVariable("id") int id,
                                   Model model) {
        Optional<JobSeekerProfile> seekerProfile = jobSeekerProfileService.getJobSeekerProfile(id);
        model.addAttribute("profile", seekerProfile.get());
        return "job-seeker-profile";
    }
}
