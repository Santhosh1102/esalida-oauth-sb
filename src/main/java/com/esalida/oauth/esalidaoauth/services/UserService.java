package com.esalida.oauth.esalidaoauth.services;

import com.esalida.oauth.esalidaoauth.config.CustomUserDetails;
import com.esalida.oauth.esalidaoauth.models.Role;
import com.esalida.oauth.esalidaoauth.models.Tenant;
import com.esalida.oauth.esalidaoauth.models.User;
import com.esalida.oauth.esalidaoauth.models.UserProfile;
import com.esalida.oauth.esalidaoauth.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final UserProfileRepository userProfileRepository;

    private final UserRoleRepository userRoleRepository;

    private final RoleRepository roleRepository;

    private final TenantRepository tenantRepository;



    @Autowired
    public UserService(UserRepository userRepository, UserProfileRepository userProfileRepository,
                       UserRoleRepository userRoleRepository,
                       RoleRepository roleRepository,
                       TenantRepository tenantRepository) {
        this.userRepository = userRepository;
        this.userProfileRepository = userProfileRepository;
        this.userRoleRepository=userRoleRepository;
        this.roleRepository=roleRepository;
        this.tenantRepository=tenantRepository;
    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    public  User findUserByName(String userName){
        User user = userRepository.findByUsername(userName);
        if(user!=null){
            List<Role> roles = roleRepository.getRolesForUserId(user.getId());
            user.setRoles(roles);
        }
        return user;
    }

    public CustomUserDetails getCustomerUserDetailsByUserName(String userName){

       User user = findUserByName(userName);
       if(user!=null){
           UserProfile userProfile = getUserProfileByUser(user);
           Tenant tenant = tenantRepository.getTenantById(user.getTenantId());
           CustomUserDetails customUserDetails = new CustomUserDetails(user);
           if(userProfile!=null){
               customUserDetails.setUserProfile(userProfile);
           }
           customUserDetails.setTenant(tenant);
           return customUserDetails;
       }
       return null;

    }



    public  User findUserById(long userId){
        return userRepository.findById(userId);
    }

    public UserProfile getUserProfileByUser(User user){
       return userProfileRepository.getUserProfileByUserId(user.getId());
    }

    public void save(User user){
        user.setPassword(getPasswordEncoder().encode(user.getPassword()));
        userRepository.save(user);
    }

    public List<Role> updateUserRoleAsAdmin(User user){
        userRoleRepository.updateRolesAsAdmin(user);
        return userRoleRepository.getUserRoles(user);
    }
    public User updatePassword(Long userId, String password){
        String encryptedPassword = getPasswordEncoder().encode(password);
        return userRepository.updatePassword(userId, encryptedPassword);
    }

}
