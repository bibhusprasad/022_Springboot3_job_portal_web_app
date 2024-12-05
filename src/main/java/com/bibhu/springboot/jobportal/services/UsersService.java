package com.bibhu.springboot.jobportal.services;

import com.bibhu.springboot.jobportal.entity.Users;
import com.bibhu.springboot.jobportal.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UsersService {

    private final UsersRepository usersRepository;

    @Autowired
    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public Users addNewUser(Users user) {
        user.setActive(true);
        user.setRegistrationDate(new Date(System.currentTimeMillis()));
        return usersRepository.save(user);
    }

    public boolean getUserByEmail(String email) {
        return usersRepository.findByEmail(email).isPresent();
    }
}
