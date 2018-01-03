package com.esalida.oauth.esalidaoauth.services;

import com.esalida.oauth.esalidaoauth.models.Tenant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;

/**
 * Created by tecnicsdev on 3/1/18.
 */
public class TenantServiceImpl implements TenantService{

    private Logger logger = LoggerFactory.getLogger(TenantServiceImpl.class);

    Tenant saveTenant(Tenant tenant){
       // validate();
       // createUserinUsertbale();
        String tenantDbName = formTenantDb(tenant.getTenantDomain());
        if(tenantDbName==null){
            return null;
        }
        tenant.setTenantDbName(tenantDbName);
        tenant.setTenantUniqueIdentifier(UUID.randomUUID().toString().split("-")[0]);
        //createTeBD();
        //createIndex();
        //putMapping();
        return null;
    }

    private String formTenantDb(String url){
        String host=null;
        if(url !=null && !url.startsWith("http") && !url.startsWith("https")){
            url = "http://" + url;
        }
        try {
            URL netUrl = new URL(url);
            host = netUrl.getHost();
            if (host.startsWith("www")) {
                host = host.substring("www".length() + 1);
            }
            host=host.replace(".","");
        }
        catch(MalformedURLException e){
            logger.error("Error parsing Tenant domain url {}", e);
        }
        return host;
    }
}
