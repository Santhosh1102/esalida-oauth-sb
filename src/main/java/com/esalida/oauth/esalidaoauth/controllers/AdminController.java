package com.esalida.oauth.esalidaoauth.controllers;


import com.esalida.oauth.esalidaoauth.models.User;
import com.esalida.oauth.esalidaoauth.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

    final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/api/admin/employee/{userId}/role")
    public ResponseEntity<User> updateUserAsAdmin(@PathVariable long userId){
        User user = userService.findUserById(userId);
        userService.updateUserRoleAsAdmin(user);
        User userUpdated =  userService.findUserByName(user.getUserName());
        return new ResponseEntity<>(userUpdated, HttpStatus.OK);
    }

}
