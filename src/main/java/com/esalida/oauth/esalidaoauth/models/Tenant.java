package com.esalida.oauth.esalidaoauth.models;


import java.io.Serializable;

public class Tenant implements Serializable{


    private static final long serialVersionUID = 3932836202968552096L;
    private Long id;
    private String tenantName;
    private String tenantDomain;
    private String tenantDbName;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTenantName() {
        return tenantName;
    }

    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
    }

    public String getTenantDomain() {
        return tenantDomain;
    }

    public void setTenantDomain(String tenantDomain) {
        this.tenantDomain = tenantDomain;
    }

    public String getTenantDbName() {
        return tenantDbName;
    }

    public void setTenantDbName(String tenantDbName) {
        this.tenantDbName = tenantDbName;
    }
}
