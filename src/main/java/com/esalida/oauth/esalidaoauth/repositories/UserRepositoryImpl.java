package com.esalida.oauth.esalidaoauth.repositories;

import com.esalida.oauth.esalidaoauth.models.Employee;
import com.esalida.oauth.esalidaoauth.models.Role;
import com.esalida.oauth.esalidaoauth.models.User;

import com.esalida.oauth.esalidaoauth.models.UserProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        try(Connection con = sql2o.open()) {
            User user =con.createQuery(QUERY_USER)
                    .addParameter("userName", username)
                    .executeAndFetchFirst(User.class);
            logger.debug("Found users:{}",user);
            return user;
        }
    }

    @Override
    public User findById(long userId) {
        String QUERY_USER_BY_ID = "select * from user where id=:userId";
        String QUERY_ROLES = "select role.id,role.name from role \n" +
                "where role.id in ( select roleId from user_role where userId=:userId)";

        try(Connection con = sql2o.open()) {
            User user =con.createQuery(QUERY_USER_BY_ID)
                    .addParameter("userId", userId)
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
    public User save(User user){
        String QUERY_USER_INSERT = "INSERT INTO `user`(`userName`,`email`,`password`,`tenantId`)\n" +
                "VALUES(:userName,:email,:password,:tenantId);";
        try(Connection con = sql2o.open()) {
            long insertedId = (long) con.createQuery(QUERY_USER_INSERT)
                   .addParameter("userName",user.getUserName())
                   .addParameter("email",user.getUserName())
                   .addParameter("password",user.getPassword())
                   .addParameter("tenantId",user.getTenantId())
                   .executeUpdate()
                   .getKey();
            return findById(insertedId);
        }
    }


    @Override
    public User findByEmailAndTenantId(String email, Long tenantId){
        String QUERY_USER = "select * from user where email=:email and tenantId=:tenantId";
        try(Connection con = sql2o.open()) {
            User user =con.createQuery(QUERY_USER)
                    .addParameter("email", email)
                    .addParameter("tenantId", tenantId)
                    .executeAndFetchFirst(User.class);
            logger.debug("Found users:{}",user);
            return user;
        }
    }

    @Override
    public User findByUsernameAndTenantId(String username, Long tenantId){
        String QUERY_USER = "select * from user where username=:username and tenantId=:tenantId";
        try(Connection con = sql2o.open()) {
            User user =con.createQuery(QUERY_USER)
                    .addParameter("username", username)
                    .addParameter("tenantId", tenantId)
                    .executeAndFetchFirst(User.class);
            logger.debug("Found users:{}",user);

            return user;
        }
    }

    @Override
    public List<Employee> fetchAllEmployess(Long tenantId) {

        String query = "select u.id,u.email, r.name, up.firstName, up.lastName from user u \n" +
                "inner join user_role ur on u.id = ur.userid \n" +
                "inner join role r on r.id = ur.roleId  \n" +
                "inner join  user_profile up on up.userId = u.id \n" +
                "where u.tenantId = :tenantId\n";
        try (Connection con = sql2o.open()) {
            List<Map<String, Object>> maps = con.createQuery(query)
                    .addParameter("tenantId", tenantId)
                    .executeAndFetchTable()
                    .asList();
            List<Employee> employeeList = new ArrayList<>();
            if (!maps.isEmpty()) {

                maps.stream().forEach(map -> {

                    System.out.println(map.keySet());
                    String firstName = (String) map.get("firstname");
                    String lastName = (String) map.get("lastname");
                    String role = (String) map.get("name");
                    Long id = (Long) map.get("id");
                    String email = (String) map.get("email");

                    Employee employee=new Employee();

                    employee.setFirstName(firstName);
                    employee.setLastName(lastName);
                    employee.setRoles(role);
                    employee.setUserId(id);
                    employee.setEmail(email);
                    employeeList.add(employee);
                });

            }
            return employeeList;
        }
    }

    @Override
    public List<Employee> fetchEmployeeDetails(Long userId) {

        String query = "select u.id,u.email, r.name, up.firstName, up.lastName from user u \n" +
                "inner join user_role ur on u.id = ur.userid \n" +
                "inner join role r on r.id = ur.roleId  \n" +
                "inner join  user_profile up on up.userId = u.id \n" +
                "where u.id = :userId\n";
        try (Connection con = sql2o.open()) {
            List<Map<String, Object>> maps = con.createQuery(query)
                    .addParameter("userId", userId)
                    .executeAndFetchTable()
                    .asList();
            List<Employee> employeeList = new ArrayList<>();
            if (!maps.isEmpty()) {
                maps.stream().forEach(map -> {
                    String firstName = (String) map.get("firstname");
                    String lastName = (String) map.get("lastname");
                    String role = (String) map.get("name");
                    Long id = (Long) map.get("id");
                    String email = (String) map.get("email");

                    Employee employee=new Employee();

                    employee.setFirstName(firstName);
                    employee.setLastName(lastName);
                    employee.setRoles(role);
                    employee.setUserId(id);
                    employee.setEmail(email);
                    employeeList.add(employee);
                });

            }
            return employeeList;
        }
    }
}
