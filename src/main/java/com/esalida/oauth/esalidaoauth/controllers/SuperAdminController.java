package com.esalida.oauth.esalidaoauth.controllers;

import com.esalida.oauth.esalidaoauth.models.Tenant;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by tecnicsdev on 3/1/18.
 */

@RestController
@RequestMapping(value = "tenant")
public class SuperAdminController {

    @RequestMapping(value = "register", method = RequestMethod.POST)
    void registerTenant(@RequestBody Tenant tenant){

    }

}
