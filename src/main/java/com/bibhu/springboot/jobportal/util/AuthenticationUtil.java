package com.bibhu.springboot.jobportal.util;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthenticationUtil {

    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public static String getCurrentUsername() {
        return getAuthentication().getName();
    }

    public static boolean isAnonymousAuthenticationTokenInstance() {
        return getAuthentication() instanceof AnonymousAuthenticationToken;
    }
}