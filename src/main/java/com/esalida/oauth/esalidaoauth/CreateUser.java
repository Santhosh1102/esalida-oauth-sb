package com.esalida.oauth.esalidaoauth;

import com.esalida.oauth.esalidaoauth.models.Employee;
import com.esalida.oauth.esalidaoauth.models.User;
import com.esalida.oauth.esalidaoauth.models.UserProfile;
import com.esalida.oauth.esalidaoauth.models.UserRole;
import com.esalida.oauth.esalidaoauth.repositories.RoleRepository;
import com.esalida.oauth.esalidaoauth.repositories.UserProfileRepository;
import com.esalida.oauth.esalidaoauth.repositories.UserRepository;
import com.esalida.oauth.esalidaoauth.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by tecnicsdev on 1/1/18.
 */
@Component
public class CreateUser implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserProfileRepository userProfileRepository;


    @Override
    public void run(String... strings) throws Exception {
        if(strings.length>0){
            String csvFile=strings[0];
            Long tenantId=new Long(strings[1]);

            BufferedReader br = null;
            String line = "";
            String cvsSplitBy = ",";

            try {

                br = new BufferedReader(new FileReader(csvFile));
                while ((line = br.readLine()) != null) {
                    // use comma as separator
                    String[] userData = line.split(cvsSplitBy);

                    Employee employee=new Employee();
                    employee.setUserId(new Long(userData[0]));
                    employee.setFirstName(userData[1]);
                    employee.setLastName(userData[2]);
                    employee.setEmail(userData[1]+userData[2]+"@enron.com");
                    employee.setUserName(userData[1]+userData[2]);
                    saveEmployee(employee, tenantId);
                }
                System.out.println("Saved all users successfully");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (br != null) {
                    try {
                        br.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private UserProfile saveEmployee(Employee employee, Long tenantId){
        String email = employee.getEmail();
        String username = employee.getUserName();

        User userByEmail=userRepository.findByEmailAndTenantId(email, tenantId);
        User userByUsername=userRepository.findByUsernameAndTenantId(username, tenantId);

        if(userByEmail == null && userByUsername == null){
            String password="password";
            String firstName=employee.getFirstName();
            String lastName=employee.getLastName();

            User user=new User();
            user.setUserName(username);
            user.setEmail(email);
            user.setPassword(userService.getPasswordEncoder().encode(password));
            user.setTenantId(tenantId);

            User savedUser = userRepository.save(user);

            UserRole role=new UserRole();
            role.setUserId(savedUser.getId());
            role.setRoleId(new Long(2));
            roleRepository.saveRole(role);

            UserProfile userProfile=new UserProfile();
            userProfile.setUserId(savedUser.getId());
            userProfile.setFirstName(firstName);
            userProfile.setLastName(lastName);

            UserProfile savedUserProfile = userProfileRepository.addUserProfile(userProfile);

            return savedUserProfile;
        }
        return null;
    }
}
