package com.esalida.oauth.esalidaoauth.models;


public class Role {


    private Long id;
    String name;

    public Role() {}

    public Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
