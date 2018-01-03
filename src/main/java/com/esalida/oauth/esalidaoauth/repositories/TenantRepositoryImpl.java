package com.esalida.oauth.esalidaoauth.repositories;

import com.esalida.oauth.esalidaoauth.models.Tenant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

@Repository
public class TenantRepositoryImpl implements TenantRepository {

    final Sql2o sql2o;

    @Autowired
    public TenantRepositoryImpl(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public Tenant getTenantById(long id) {
        String QUERY_GET_TENANT= "select * from tenant where id=:tenantId";
        try(Connection connection = sql2o.open()){
            return connection.createQuery(QUERY_GET_TENANT)
                    .addParameter("tenantId",id)
                    .executeAndFetchFirst(Tenant.class);
        }
    }

    @Override
    public Tenant save(Tenant tenant) {
        String QUERY_TENANT_INSERT = "INSERT INTO `tenant`(`id`,`tenantName`,`tenantDomain`,`tenantDbName`, `tenantUniqueIdentifier`)\n" +
                "VALUES(:id,:tenantName,:tenantDomain,:tenantDbName, :tenantUniqueIdentifier);";
        try(Connection con = sql2o.open()) {
            long insertedId = (long) con.createQuery(QUERY_TENANT_INSERT)
                    .bind(tenant)
                    .executeUpdate()
                    .getKey();

            return getTenantById(insertedId);
        }
    }


}
