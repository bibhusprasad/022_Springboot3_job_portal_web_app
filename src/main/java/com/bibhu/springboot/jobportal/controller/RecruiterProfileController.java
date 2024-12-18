package com.bibhu.springboot.jobportal.controller;

import com.bibhu.springboot.jobportal.entity.RecruiterProfile;
import com.bibhu.springboot.jobportal.entity.Users;
import com.bibhu.springboot.jobportal.services.RecruiterProfileService;
import com.bibhu.springboot.jobportal.services.UsersService;
import com.bibhu.springboot.jobportal.util.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;
import java.util.Optional;

import static com.bibhu.springboot.jobportal.util.AuthenticationUtil.getCurrentUsername;
import static com.bibhu.springboot.jobportal.util.AuthenticationUtil.isAnonymousAuthenticationTokenInstance;

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
        if (!isAnonymousAuthenticationTokenInstance()) {
            String currentUserName = getCurrentUsername();
            Users users = usersService.findUserByEmail(currentUserName)
                    .orElseThrow(() -> new UsernameNotFoundException("Could not found user " + currentUserName));
            Optional<RecruiterProfile> recruiterProfileOptional = recruiterProfileService.findUserProfile(users.getUserId());
            recruiterProfileOptional.ifPresent(recruiterProfile -> model.addAttribute("profile", recruiterProfile));
        }
        return "recruiter_profile";
    }

    @PostMapping("/addNew")
    public String addNewRecruiter(RecruiterProfile recruiterProfile,
                         Model model,
                         @RequestParam("image") MultipartFile multipartFile) {
        if (!isAnonymousAuthenticationTokenInstance()){
            String currentUserName = getCurrentUsername();
            Users users = usersService.findUserByEmail(currentUserName)
                    .orElseThrow(() -> new UsernameNotFoundException("Could not found user " + currentUserName));
            recruiterProfile.setUserId(users);
            recruiterProfile.setUserAccountId(users.getUserId());
        }
        model.addAttribute("profile", recruiterProfile);
        String fileName = "";
        if (StringUtils.hasLength(multipartFile.getOriginalFilename())) {
            fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
            recruiterProfile.setProfilePhoto(fileName);
        }
        RecruiterProfile savedUser = recruiterProfileService.addNewRecruiter(recruiterProfile);
        String uploadedDir = "photos/recruiter/" + savedUser.getUserAccountId();
        try{
            FileUploadUtil.saveFile(uploadedDir, fileName, multipartFile);
        } catch (Exception e) {
            throw new RuntimeException("Could not upload file", e);
        }
        return "redirect:/dashboard/";
    }

}
