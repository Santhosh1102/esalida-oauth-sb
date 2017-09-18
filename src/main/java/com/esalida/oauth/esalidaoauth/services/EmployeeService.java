package com.esalida.oauth.esalidaoauth.services;

import com.esalida.oauth.esalidaoauth.models.Employee;
import com.esalida.oauth.esalidaoauth.models.UserProfile;

/**
 * Created by tecnicsdev on 16/9/17.
 */
public interface EmployeeService {

    UserProfile saveEmployee(Employee employee);
}
