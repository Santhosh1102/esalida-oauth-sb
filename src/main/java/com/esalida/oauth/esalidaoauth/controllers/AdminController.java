package com.esalida.oauth.esalidaoauth.controllers;


import com.esalida.oauth.esalidaoauth.models.Employee;
import com.esalida.oauth.esalidaoauth.models.User;
import com.esalida.oauth.esalidaoauth.models.UserProfile;
import com.esalida.oauth.esalidaoauth.models.UserRole;
import com.esalida.oauth.esalidaoauth.services.EmployeeService;
import com.esalida.oauth.esalidaoauth.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/admin/")
public class AdminController {

    final UserService userService;
    final EmployeeService employeeService;

    @Autowired
    public AdminController(UserService userService, EmployeeService employeeService) {
        this.userService = userService;
        this.employeeService=employeeService;
    }

    @RequestMapping(value = "employee/{userId}/role/{rolename}")
    public ResponseEntity<User> updateUserAsAdmin(@PathVariable long userId){
        User user = userService.findUserById(userId);
        userService.updateUserRoleAsAdmin(user);
        User userUpdated =  userService.findUserByName(user.getUserName());
        return new ResponseEntity<>(userUpdated, HttpStatus.OK);
    }

    @RequestMapping(value = "employee", method = RequestMethod.POST)
    ResponseEntity<UserProfile> saveEmployee(@Valid @RequestBody Employee employee){
        UserProfile userProfile = employeeService.saveEmployee(employee);
        if(userProfile==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity<>(userProfile, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "password", method = RequestMethod.POST)
    ResponseEntity<User> updatePassword(RequestEntity<User> userInfo) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> principal = authentication.getAuthorities();

        boolean adminFlag = principal.stream().anyMatch(auth->auth.getAuthority().contains("ROLE_ADMIN"));
        if(adminFlag){
            User user = userService.updatePassword(userInfo.getBody());
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "employee/all")
    ResponseEntity<List<Employee>> getAllEmployees(){
        List<Employee> allEmployees = employeeService.getAllEmployees();
        return new ResponseEntity<>(allEmployees, HttpStatus.OK);
    }

    @RequestMapping(value = "employee/{id}/profile", method = RequestMethod.POST)
    ResponseEntity<UserProfile> updateUserProfile(@Valid @RequestBody  UserProfile userProfile){
        UserProfile userProfileFromService = employeeService.updateUserProfile(userProfile);
        if(userProfileFromService==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }


    @RequestMapping(value = "employee/{userId}/profile", method = RequestMethod.GET)
    ResponseEntity<List<Employee>> getUserProfile(@PathVariable Long userId){
        List<Employee> employeeList = employeeService.fetchEmployeeBasedOnId(userId);
        if(employeeList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(employeeList, HttpStatus.OK);
    }


    @RequestMapping(value = "employee/{userId}/role/{rolename}", method = RequestMethod.POST)
    ResponseEntity<UserRole> updateUserRole(@PathVariable long userId, @PathVariable String rolename){
        boolean isUpdated=employeeService.updateRole(userId, rolename);
        if(isUpdated){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
