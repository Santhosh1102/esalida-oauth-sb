package com.esalida.oauth.esalidaoauth.repositories;


import com.esalida.oauth.esalidaoauth.models.User;


/**
 * User repository for CRUD operations.
 */
public interface UserRepository {
    User findByUsername(String username);
    User findById(long userId);
    User save(User user);
    User findByEmailAndTenantId(String email, Long tenantId);
    User findByUsernameAndTenantId(String username, Long tenantId);

}
