package com.esalida.oauth.esalidaoauth.repositories;


import com.esalida.oauth.esalidaoauth.models.Tenant;

public interface TenantRepository {

    Tenant getTenantById(long id);
    Tenant save(Tenant tenant);

}
