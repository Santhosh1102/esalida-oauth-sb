package com.esalida.oauth.esalidaoauth.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/user").authenticated()
                .antMatchers("/admin/employee/{userId}/role/{rolename}").hasAnyAuthority("ROLE_ADMIN", "ADMIN")
                .antMatchers("/admin/employee").hasAnyAuthority("ROLE_ADMIN", "ADMIN")
                .antMatchers("/admin/employee/all").hasAnyAuthority("ROLE_ADMIN", "ADMIN")
                .antMatchers("/admin/employee/{id}/profile").hasAnyRole("ROLE_ADMIN", "ADMIN")
                .antMatchers("/admin/employee/{userId}/profile").hasAnyRole("ROLE_ADMIN", "ADMIN")
                .antMatchers("/admin/employee/{userId}/role/{rolename}").hasAnyAuthority("ROLE_ADMIN", "ADMIN");
    }
}
