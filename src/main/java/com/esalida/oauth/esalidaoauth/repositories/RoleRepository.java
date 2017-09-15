package com.esalida.oauth.esalidaoauth.repositories;

import com.esalida.oauth.esalidaoauth.models.Role;

import java.util.List;

public interface RoleRepository {

    List<Role> getRolesForUserId(long userId);
}
