package com.esalida.oauth.esalidaoauth.repositories;

import com.esalida.oauth.esalidaoauth.models.UserProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;


@Repository
public class UserProfileRepositoryImpl implements UserProfileRepository{

    private Logger logger = LoggerFactory.getLogger(UserRepositoryImpl.class);

    private final Sql2o sql2o;

    @Autowired
    public UserProfileRepositoryImpl(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public UserProfile getUserProfileByUserId(Long userId) {
        String QUERY_USER_INSERT = "select * from user_profile where userId=:userId";
        try(Connection con = sql2o.open()) {
            return con.createQuery(QUERY_USER_INSERT)
                    .addParameter("userId", userId)
                    .executeAndFetchFirst(UserProfile.class);
        }
    }

    @Override
    public UserProfile addUserProfile(UserProfile userProfile) {
        String QUERY_USER_INSERT = "INSERT INTO `user_profile`(`userId`,`firstName`,`lastName`)\n" +
                "VALUES(:userId, :firstName, :lastName);";
        try(Connection con = sql2o.open()) {
            con.createQuery(QUERY_USER_INSERT)
                    .addParameter("userId",userProfile.getUserId())
                    .addParameter("firstName",userProfile.getFirstName())
                    .addParameter("lastName",userProfile.getLastName())
                    .executeUpdate()
                    .getKey();
            return getUserProfileByUserId(userProfile.getUserId());
        }
    }

    @Override
    public UserProfile updateUserProfile(UserProfile userProfile) {
        String QUERY_USER_INSERT = "UPDATE `user_profile` SET firstName=:firstName, lastName=:lastName\n" +
                "where userId=:userId;";
        try(Connection con = sql2o.open()) {
            con.createQuery(QUERY_USER_INSERT)
                    .addParameter("userId",userProfile.getUserId())
                    .addParameter("firstName",userProfile.getFirstName())
                    .addParameter("lastName",userProfile.getLastName())
                    .executeUpdate()
                    .getKey();
            return getUserProfileByUserId(userProfile.getUserId());
        }
    }
}
