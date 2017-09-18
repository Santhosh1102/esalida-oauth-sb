package com.esalida.oauth.esalidaoauth.models;

import java.util.Date;
import java.util.List;


public class User {
    private Long id;
    private String userName;
    private String email;
    private String password;
    private List<Role> roles;
    private Long tenantId;
    private Date created_at;
    private Date updated_at;

    public User() {}

    public User(String userName, String email, String password, List<Role> roles, Long tenantId) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.tenantId = tenantId;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String username) {
        this.userName = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                ", tenantId=" + tenantId +
                ", created_at=" + created_at +
                ", updated_at=" + updated_at +
                '}';
    }
}
