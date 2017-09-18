package com.esalida.oauth.esalidaoauth.models;

import java.io.Serializable;

public class UserProfile implements Serializable {

    private static final long serialVersionUID = 8117675143388035031L;
    private long userId;
    private String firstName;
    private String lastName;


     public long getUserId() {
         return userId;
     }

     public void setUserId(long userId) {
         this.userId = userId;
     }

     public String getFirstName() {
         return firstName;
     }

     public void setFirstName(String firstName) {
         this.firstName = firstName;
     }

     public String getLastName() {
         return lastName;
     }

     public void setLastName(String lastName) {
         this.lastName = lastName;
     }
 }
