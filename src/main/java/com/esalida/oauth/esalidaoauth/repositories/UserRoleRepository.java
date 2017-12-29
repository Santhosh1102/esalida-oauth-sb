package com.esalida.oauth.esalidaoauth.repositories;


import com.esalida.oauth.esalidaoauth.models.Role;
import com.esalida.oauth.esalidaoauth.models.User;
import com.esalida.oauth.esalidaoauth.models.UserRole;

import java.util.List;

public interface UserRoleRepository {

    void updateRolesAsAdmin(User user);

    List<Role> getUserRoles(User user);

    boolean updateRole(UserRole userRole);

    void clearRolesForUser(Long userID);

}
