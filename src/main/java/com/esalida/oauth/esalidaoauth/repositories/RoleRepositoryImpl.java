package com.esalida.oauth.esalidaoauth.repositories;

import com.esalida.oauth.esalidaoauth.models.Role;
import com.esalida.oauth.esalidaoauth.models.UserRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

@Repository
public class RoleRepositoryImpl implements RoleRepository {

    private Logger logger = LoggerFactory.getLogger(RoleRepositoryImpl.class);

    final Sql2o sql2o;

    @Autowired
    public RoleRepositoryImpl(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public List<Role> getRolesForUserId(long userId) {
        String QUERY_ROLES = "select role.id,role.name from role \n" +
                "where role.id in ( select roleId from user_role where userId=:userId)";

        try (Connection connection = sql2o.open()) {
            List<Role> roles = connection.createQuery(QUERY_ROLES)
                    .addParameter("userId", userId)
                    .executeAndFetch(Role.class);
            logger.debug("Found roles for user:{}:{}", userId, roles.size());
            return roles;
        }

    }

    public void saveRole(UserRole role) {
        String QUERY_USER_INSERT = "INSERT INTO `user_role`(`userId`,`roleId`)\n" +
                "VALUES(:userId, :roleId);";
        try (Connection con = sql2o.open()) {
            con.createQuery(QUERY_USER_INSERT)
                    .addParameter("userId", role.getUserId())
                    .addParameter("roleId", role.getRoleId())
                    .executeUpdate()
                    .getKey();
        }
    }

}