package com.esalida.oauth.esalidaoauth.repositories;

import com.esalida.oauth.esalidaoauth.models.Role;
import com.esalida.oauth.esalidaoauth.models.User;
import com.esalida.oauth.esalidaoauth.models.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

@Repository
public class UserRoleRepositoryImpl implements UserRoleRepository {

    final Sql2o sql2o;

    @Autowired
    public UserRoleRepositoryImpl(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void updateRolesAsAdmin(User user) {
        String QUERY_CHECK_FOR_ADMIN_ROLE = "select role.name from role \n" +
                "INNER JOIN user_role on role.id=user_role.roleId\n" +
                "where user_role.userId=:userId and role.name='ADMIN'; ";

        String QUERY_INSERT_ADMIN_ROLE = "INSERT INTO user_role (userId,roleId) " +
                "select :userId,role.id from role where name='ADMIN';\n";

        try(Connection con = sql2o.open()) {
            Role role = con.createQuery(QUERY_CHECK_FOR_ADMIN_ROLE)
                    .addParameter("userId",user.getId())
                    .executeAndFetchFirst(Role.class);
            if(role!=null){
                con.createQuery(QUERY_INSERT_ADMIN_ROLE)
                        .addParameter("userId",user.getId())
                        .executeUpdate();
            }
        }
    }

    @Override
    public List<Role> getUserRoles(User user) {

        String QUERY_GET_USER_ROLES = "select role.name from role \n" +
                "INNER JOIN user_role on role.id=user_role.roleId\n" +
                "where user_role.userId=userId";
        try(Connection con = sql2o.open()) {
            List<Role> roles = con.createQuery(QUERY_GET_USER_ROLES)
                    .addParameter("userId",user.getId())
                    .executeAndFetch(Role.class);

            return roles;
        }
    }


    @Override
    public boolean updateRole(UserRole userRole){
        String query = "update user_role set roleId=:roleId where userId=:userId";
        try(Connection con = sql2o.open()) {

            con.createQuery(query)
                    .addParameter("userId", userRole.getUserId())
                    .addParameter("roleId", userRole.getRoleId())
                    .executeUpdate()
                    .getKey();
            return true;

        }
    }

    @Override
    public void clearRolesForUser(Long userID) {

    }
}
