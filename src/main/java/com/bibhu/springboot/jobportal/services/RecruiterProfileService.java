package com.bibhu.springboot.jobportal.services;

import com.bibhu.springboot.jobportal.entity.RecruiterProfile;
import com.bibhu.springboot.jobportal.entity.Users;
import com.bibhu.springboot.jobportal.repository.RecruiterProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.bibhu.springboot.jobportal.util.AuthenticationUtil.isAnonymousAuthenticationTokenInstance;

@Service
public class RecruiterProfileService {

    private final RecruiterProfileRepository recruiterProfileRepository;
    private final UsersService usersService;

    @Autowired
    public RecruiterProfileService(RecruiterProfileRepository recruiterProfileRepository,
                                   UsersService usersService) {
        this.recruiterProfileRepository = recruiterProfileRepository;
        this.usersService = usersService;
    }

    public Optional<RecruiterProfile> findUserProfile(Integer id) {
        return recruiterProfileRepository.findById(id);
    }

    public RecruiterProfile addNewRecruiter(RecruiterProfile recruiterProfile) {
        return recruiterProfileRepository.save(recruiterProfile);
    }

    public RecruiterProfile getCurrentRecruiterProfile() {
        if (!isAnonymousAuthenticationTokenInstance()) {
            Users users = usersService.getCurrentUser();
            Optional<RecruiterProfile> recruiterProfile = findUserProfile(users.getUserId());
            return recruiterProfile.orElse(null );
        } else {
            return null;
        }
    }
}
