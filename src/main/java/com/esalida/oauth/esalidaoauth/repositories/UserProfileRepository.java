package com.esalida.oauth.esalidaoauth.repositories;


import com.esalida.oauth.esalidaoauth.models.UserProfile;

public interface UserProfileRepository {

    UserProfile getUserProfileByUserId(Long userId);
    UserProfile addUserProfile(UserProfile userProfile);
    UserProfile updateUserProfile(UserProfile userProfile);

    }
