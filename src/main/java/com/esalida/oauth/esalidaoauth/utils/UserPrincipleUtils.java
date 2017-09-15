package com.esalida.oauth.esalidaoauth.utils;


import com.esalida.oauth.esalidaoauth.config.CustomUserDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class UserPrincipleUtils {

    public static CustomUserDetails getPrinciple(){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!authentication.getPrincipal().equals("anonymous")){
            CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
            return  customUserDetails;
        }
        return null;
    }
}
