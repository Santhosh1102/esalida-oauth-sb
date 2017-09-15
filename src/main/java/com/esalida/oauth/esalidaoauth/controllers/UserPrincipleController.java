package com.esalida.oauth.esalidaoauth.controllers;

import com.esalida.oauth.esalidaoauth.config.CustomUserDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserPrincipleController {

    @RequestMapping(value = "/user")
    public ResponseEntity<CustomUserDetails> getUserPrinciple(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
       if(!authentication.getPrincipal().equals("anonymous")){
               CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
               return new ResponseEntity<CustomUserDetails>(customUserDetails, HttpStatus.OK);
       }
       return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
