package com.esalida.oauth.esalidaoauth.controllers;


import com.esalida.oauth.esalidaoauth.models.Employee;
import com.esalida.oauth.esalidaoauth.models.User;
import com.esalida.oauth.esalidaoauth.models.UserProfile;
import com.esalida.oauth.esalidaoauth.services.EmployeeService;
import com.esalida.oauth.esalidaoauth.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class AdminController {

    final UserService userService;
    final EmployeeService employeeService;

    @Autowired
    public AdminController(UserService userService, EmployeeService employeeService) {
        this.userService = userService;
        this.employeeService=employeeService;
    }

    @RequestMapping(value = "/api/admin/employee/{userId}/role")
    public ResponseEntity<User> updateUserAsAdmin(@PathVariable long userId){
        User user = userService.findUserById(userId);
        userService.updateUserRoleAsAdmin(user);
        User userUpdated =  userService.findUserByName(user.getUserName());
        return new ResponseEntity<>(userUpdated, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/admin/employee", method = RequestMethod.POST)
    ResponseEntity<UserProfile> saveEmployee(@Valid @RequestBody Employee employee){
        UserProfile userProfile = employeeService.saveEmployee(employee);
        if(userProfile==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity<>(userProfile, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/api/admin/employee/all")
    ResponseEntity<List<Employee>> getAllEmployees(){
        List<Employee> allEmployees = employeeService.getAllEmployees();
        return new ResponseEntity<>(allEmployees, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/admin/employee/{id}/profile", method = RequestMethod.POST)
    ResponseEntity<UserProfile> updateUserProfile(@Valid @RequestBody  UserProfile userProfile){
        UserProfile userProfileFromService = employeeService.updateUserProfile(userProfile);
        if(userProfileFromService==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
}
