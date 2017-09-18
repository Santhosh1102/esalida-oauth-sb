package com.esalida.oauth.esalidaoauth.controllers;

import com.esalida.oauth.esalidaoauth.models.Employee;
import com.esalida.oauth.esalidaoauth.models.User;
import com.esalida.oauth.esalidaoauth.models.UserProfile;
import com.esalida.oauth.esalidaoauth.repositories.UserRepository;
import com.esalida.oauth.esalidaoauth.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Created by tecnicsdev on 16/9/17.
 */
@RestController
public class EmployeeController {

    EmployeeService employeeService;

    @Autowired
    EmployeeController(EmployeeService employeeService){
        this.employeeService=employeeService;
    }

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/api/admin/employee", method = RequestMethod.POST)
    ResponseEntity<UserProfile> saveEmployee(@Valid @RequestBody Employee employee){
        UserProfile userProfile = employeeService.saveEmployee(employee);
        if(userProfile==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity<>(userProfile, HttpStatus.OK);
        }

    }

}
