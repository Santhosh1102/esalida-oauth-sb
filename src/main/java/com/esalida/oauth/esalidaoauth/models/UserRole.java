package com.esalida.oauth.esalidaoauth.models;

/**
 * Created by tecnicsdev on 18/9/17.
 */
public class UserRole {

    private Long userId;
    private Long RoleId;


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return RoleId;
    }

    public void setRoleId(Long roleId) {
        RoleId = roleId;
    }
}
