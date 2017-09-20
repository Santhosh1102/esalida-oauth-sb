package com.esalida.oauth.esalidaoauth.services;

import com.esalida.oauth.esalidaoauth.models.*;
import com.esalida.oauth.esalidaoauth.repositories.RoleRepository;
import com.esalida.oauth.esalidaoauth.repositories.UserProfileRepository;
import com.esalida.oauth.esalidaoauth.repositories.UserRepository;
import com.esalida.oauth.esalidaoauth.repositories.UserRepositoryImpl;
import com.esalida.oauth.esalidaoauth.utils.UserPrincipleUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Created by tecnicsdev on 16/9/17.
 */

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);


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
            String password=generatePassword();
            logger.info("Password {}", password);
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
        return null;
    }


    public String generatePassword(){
        String password = UUID.randomUUID().toString().replaceAll("-", "");
        password = password.substring(0, 10);
        return  password;
    }

    @Override
    public List<Employee> getAllEmployees(){
        Long tenantId = UserPrincipleUtils.getPrinciple().getTenant().getId();
        return userRepository.fetchAllEmployess(tenantId);
    }

    @Override
    public UserProfile updateUserProfile(UserProfile userProfile){

        UserProfile userProfileFromDb = userProfileRepository.getUserProfileByUserId(userProfile.getUserId());
        if(userProfileFromDb!=null){

            userProfileFromDb.setFirstName(userProfile.getFirstName());
            userProfileFromDb.setLastName(userProfile.getLastName());

            return userProfileRepository.updateUserProfile(userProfile);

        }else {
            return null;
        }
    }
}
