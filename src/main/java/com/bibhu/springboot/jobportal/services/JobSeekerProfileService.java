package com.bibhu.springboot.jobportal.services;

import com.bibhu.springboot.jobportal.entity.JobSeekerProfile;
import com.bibhu.springboot.jobportal.entity.RecruiterProfile;
import com.bibhu.springboot.jobportal.entity.Users;
import com.bibhu.springboot.jobportal.repository.JobSeekerProfileRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.bibhu.springboot.jobportal.util.AuthenticationUtil.isAnonymousAuthenticationTokenInstance;

@Service
public class JobSeekerProfileService {

    private final JobSeekerProfileRepository jobSeekerProfileRepository;
    private final UsersService usersService;

    public JobSeekerProfileService(JobSeekerProfileRepository jobSeekerProfileRepository,
                                   UsersService usersService) {
        this.jobSeekerProfileRepository = jobSeekerProfileRepository;
        this.usersService = usersService;
    }

    public Optional<JobSeekerProfile> getJobSeekerProfile(Integer id) {
        return jobSeekerProfileRepository.findById(id);
    }

    public JobSeekerProfile addNewJobSeeker(JobSeekerProfile jobSeekerProfile) {
        return jobSeekerProfileRepository.save(jobSeekerProfile);
    }

    public JobSeekerProfile getCurrentJobSeekerProfile() {
        if (!isAnonymousAuthenticationTokenInstance()) {
            Users users = usersService.getCurrentUser();
            Optional<JobSeekerProfile> jobSeekerProfile = getJobSeekerProfile(users.getUserId());
            return jobSeekerProfile.orElse(null );
        } else {
            return null;
        }
    }
}
