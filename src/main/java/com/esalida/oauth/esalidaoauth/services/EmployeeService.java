package com.esalida.oauth.esalidaoauth.services;

import com.esalida.oauth.esalidaoauth.models.Employee;
import com.esalida.oauth.esalidaoauth.models.UserProfile;

import java.util.List;
import java.util.Map;

/**
 * Created by tecnicsdev on 16/9/17.
 */
public interface EmployeeService {

    UserProfile saveEmployee(Employee employee);
    List<Employee> getAllEmployees();
    UserProfile updateUserProfile(UserProfile userProfile);
}
