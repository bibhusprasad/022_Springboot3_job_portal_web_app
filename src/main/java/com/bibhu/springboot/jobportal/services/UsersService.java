package com.bibhu.springboot.jobportal.services;

import com.bibhu.springboot.jobportal.entity.JobSeekerProfile;
import com.bibhu.springboot.jobportal.entity.RecruiterProfile;
import com.bibhu.springboot.jobportal.entity.Users;
import com.bibhu.springboot.jobportal.repository.JobSeekerProfileRepository;
import com.bibhu.springboot.jobportal.repository.RecruiterProfileRepository;
import com.bibhu.springboot.jobportal.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

import static com.bibhu.springboot.jobportal.util.AuthenticationUtil.getAuthentication;
import static com.bibhu.springboot.jobportal.util.AuthenticationUtil.getCurrentUsername;
import static com.bibhu.springboot.jobportal.util.AuthenticationUtil.isAnonymousAuthenticationTokenInstance;

@Service
public class UsersService {

    private final UsersRepository usersRepository;
    private final JobSeekerProfileRepository jobSeekerProfileRepository;
    private final RecruiterProfileRepository recruiterProfileRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UsersService(UsersRepository usersRepository,
                        JobSeekerProfileRepository jobSeekerProfileRepository,
                        RecruiterProfileRepository recruiterProfileRepository,
                        PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.jobSeekerProfileRepository = jobSeekerProfileRepository;
        this.recruiterProfileRepository = recruiterProfileRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Users addNewUser(Users user) {
        user.setActive(true);
        user.setRegistrationDate(new Date(System.currentTimeMillis()));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Users savedUser = usersRepository.save(user);
        int userTypeId = user.getUserTypeId().getUserTypeId();
        if (userTypeId == 1) {
            recruiterProfileRepository.save(new RecruiterProfile(savedUser));
        } else {
            jobSeekerProfileRepository.save(new JobSeekerProfile(savedUser));
        }
        return savedUser;
    }

    public boolean checkForExistingEmailId(String email) {
        return findUserByEmail(email).isPresent();
    }

    public Optional<Users> findUserByEmail(String currentUserName) {
        return usersRepository.findByEmail(currentUserName);
    }

    public Object getCurrentUserProfile() {
        if (!isAnonymousAuthenticationTokenInstance()) {
            String userName = getCurrentUsername();
            Users user = findUserByEmail(userName)
                    .orElseThrow(() -> new UsernameNotFoundException("Could not find user " + userName));
            int userId = user.getUserId();
            if (getAuthentication().getAuthorities().contains(new SimpleGrantedAuthority("Recruiter"))) {
                return recruiterProfileRepository.findById(userId)
                        .orElse(new RecruiterProfile());
            } else {
                return jobSeekerProfileRepository.findById(userId)
                        .orElse(new JobSeekerProfile());
            }
        }
        return null;
    }

}
