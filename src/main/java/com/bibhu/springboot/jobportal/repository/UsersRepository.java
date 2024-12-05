package com.bibhu.springboot.jobportal.repository;

import com.bibhu.springboot.jobportal.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Integer> {

}
