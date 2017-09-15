package com.esalida.oauth.esalidaoauth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.sql2o.Sql2o;

import javax.sql.DataSource;

@Configuration
public class ConnectionsConfig {


    @Bean
    @Autowired
    public Sql2o getSql2o(DataSource dataSource){

        return new Sql2o(dataSource);
    }

}
