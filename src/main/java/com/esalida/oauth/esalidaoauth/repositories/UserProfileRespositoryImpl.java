package com.esalida.oauth.esalidaoauth.repositories;

import com.esalida.oauth.esalidaoauth.models.User;
import com.esalida.oauth.esalidaoauth.models.UserProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;


@Repository
public class UserProfileRespositoryImpl implements UserProfileRepository{

    private Logger logger = LoggerFactory.getLogger(UserRepositoryImpl.class);

    private final Sql2o sql2o;

    @Autowired
    public UserProfileRespositoryImpl(Sql2o sql2o) {
        this.sql2o = sql2o;
    }


    @Override
    public UserProfile getUserProfileByUser(User user) {
        String QUERY_USER_INSERT = "select * from user_profile where userId=:userId";
        try(Connection con = sql2o.open()) {
            return con.createQuery(QUERY_USER_INSERT)
                    .addParameter("userId",user.getId())
                    .executeAndFetchFirst(UserProfile.class);
        }
    }

    @Override
    public UserProfile getUserProfileByUserId(Long userId) {
        return null;
    }
}
