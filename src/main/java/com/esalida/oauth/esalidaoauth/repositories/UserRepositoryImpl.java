package com.esalida.oauth.esalidaoauth.repositories;

import com.esalida.oauth.esalidaoauth.models.Role;
import com.esalida.oauth.esalidaoauth.models.User;

import com.esalida.oauth.esalidaoauth.models.UserProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private Logger logger = LoggerFactory.getLogger(UserRepositoryImpl.class);
    private final Sql2o sql2o;

    @Autowired
    public UserRepositoryImpl(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public User findByUsername(String username) {
        String QUERY_USER = "select * from user where userName=:userName";
//        String QUERY_ROLES = "select role.id,role.name from role \n" +
//                "INNER JOIN user_role on user_role.roleId = role.id\n" +
//                "INNER JOIN user on user.id =user_role.userId\n" +
//                "where user.id=:userID";
        String QUERY_ROLES = "select role.id,role.name from role \n" +
                "where role.id in ( select roleId from user_role where userId=:userId)";

        try(Connection con = sql2o.open()) {
            User user =con.createQuery(QUERY_USER)
                    .addParameter("userName", username)
                    .executeAndFetchFirst(User.class);
            logger.debug("Found users:{}",user);
            if(user==null){
                return null;
            }
            List<Role> roles =con.createQuery(QUERY_ROLES)
                    .addParameter("userId", user.getId())
                    .executeAndFetch(Role.class);
            logger.debug("Found roles for user:{}:{}",user.getId(),roles.size());
            user.setRoles(roles);
            return user;
        }
    }

    @Override
    public void save(User user){
        String QUERY_USER_INSERT = "INSERT INTO `esalidaoauth`.`user`(`userName`,`email`,`password`,`tenantId`)\n" +
                "VALUES(:userName,:email,:password,:tenantId);";
        try(Connection con = sql2o.open()) {
           con.createQuery(QUERY_USER_INSERT)
                   .addParameter("userName",user.getUserName())
                   .addParameter("email",user.getUserName())
                   .addParameter("password",user.getPassword())
                   .addParameter("tenantId",user.getTenantId())
                   .executeUpdate();
        }
    }
}
