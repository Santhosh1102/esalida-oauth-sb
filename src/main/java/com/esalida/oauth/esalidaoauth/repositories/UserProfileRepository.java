package com.esalida.oauth.esalidaoauth.repositories;


import com.esalida.oauth.esalidaoauth.models.User;
import com.esalida.oauth.esalidaoauth.models.UserProfile;

public interface UserProfileRepository {

    UserProfile getUserProfileByUser(User user);
    UserProfile getUserProfileByUserId(Long userId);
    User addUser(User user);

}
