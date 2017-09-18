package com.esalida.oauth.esalidaoauth.services;

import com.esalida.oauth.esalidaoauth.models.*;
import com.esalida.oauth.esalidaoauth.repositories.RoleRepository;
import com.esalida.oauth.esalidaoauth.repositories.UserProfileRepository;
import com.esalida.oauth.esalidaoauth.repositories.UserRepository;
import com.esalida.oauth.esalidaoauth.utils.UserPrincipleUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by tecnicsdev on 16/9/17.
 */

@Service
public class EmployeeServiceImpl implements EmployeeService {

    UserRepository userRepository;
    UserProfileRepository userProfileRepository;
    RoleRepository roleRepository;
    @Autowired
    EmployeeServiceImpl(UserRepository userRepository, UserProfileRepository userProfileRepository, RoleRepository roleRepository){

        this.userRepository=userRepository;
        this.userProfileRepository=userProfileRepository;
        this.roleRepository=roleRepository;
    }

    @Override
    public UserProfile saveEmployee(Employee employee){
        String email = employee.getEmail();
        String username = employee.getUserName();
        Long tenantId = UserPrincipleUtils.getPrinciple().getTenant().getId();

        User userByEmail=userRepository.findByEmailAndTenantId(email, tenantId);
        User userByUsername=userRepository.findByUsernameAndTenantId(username, tenantId);

        if(userByEmail == null && userByUsername == null){
            System.out.println("Not Found");
            String password=employee.getPassword();
            String firstName=employee.getFirstName();
            String lastName=employee.getLastName();

            User user=new User();
            user.setUserName(username);
            user.setEmail(email);
            user.setPassword(password);

            User savedUser = userRepository.save(user);

            UserRole role=new UserRole();
            role.setUserId(savedUser.getId());
            role.setRoleId(new Long(2));
            roleRepository.saveRole(role);

            UserProfile userProfile=new UserProfile();
            userProfile.setUserId(savedUser.getId());
            userProfile.setFirstName(firstName);
            userProfile.setLastName(lastName);

            UserProfile savedUserProfile = userProfileRepository.addUserProfile(userProfile);

            return savedUserProfile;
        }
        System.out.println("Found");
        return null;
    }
}
