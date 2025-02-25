package com.bibhu.springboot.jobportal.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();
        log.info("The username {} is logged in.", username);
        boolean hasJobSeekerRole = authentication.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals("Job Seeker"));
        boolean hasRecruiterRole = authentication.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals("Recruiter"));
        if (hasJobSeekerRole || hasRecruiterRole) {
            response.sendRedirect("/dashboard/");
        }
    }
}
