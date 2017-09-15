package com.esalida.oauth.esalidaoauth.services;

import com.esalida.oauth.esalidaoauth.models.User;
import com.esalida.oauth.esalidaoauth.models.UserProfile;
import com.esalida.oauth.esalidaoauth.repositories.UserProfileRepository;
import com.esalida.oauth.esalidaoauth.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository repo;

    private final UserProfileRepository userProfileRepository;

    @Autowired
    public UserService(UserRepository repo, UserProfileRepository userProfileRepository) {
        this.repo = repo;
        this.userProfileRepository = userProfileRepository;
    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    public  User findUserByName(String userName){
        return repo.findByUsername(userName);
    }

    public UserProfile getUserProfileByUser(User user){
       return userProfileRepository.getUserProfileByUser(user);
    }

    public void save(User user){
        user.setPassword(getPasswordEncoder().encode(user.getPassword()));
        repo.save(user);
    }

}
