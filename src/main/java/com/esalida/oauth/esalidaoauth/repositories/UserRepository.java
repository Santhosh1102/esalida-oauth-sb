package com.esalida.oauth.esalidaoauth.repositories;


import com.esalida.oauth.esalidaoauth.models.User;


/**
 * User repository for CRUD operations.
 */
public interface UserRepository {
    User findByUsername(String username);
    User findById(long userId);
    void save(User user);

}
