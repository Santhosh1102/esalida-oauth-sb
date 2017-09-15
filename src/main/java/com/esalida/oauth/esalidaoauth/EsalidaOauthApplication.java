package com.esalida.oauth.esalidaoauth;

import com.esalida.oauth.esalidaoauth.config.CustomUserDetails;
import com.esalida.oauth.esalidaoauth.models.User;
import com.esalida.oauth.esalidaoauth.models.UserProfile;
import com.esalida.oauth.esalidaoauth.repositories.UserRepository;
import com.esalida.oauth.esalidaoauth.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class EsalidaOauthApplication {


    @Autowired
    private PasswordEncoder passwordEncoder;


	public static void main(String[] args) {
		SpringApplication.run(EsalidaOauthApplication.class, args);
	}

	@Autowired
	public void authenticationManager(AuthenticationManagerBuilder builder, UserRepository repository, UserService service) throws Exception {
		builder.userDetailsService(userDetailsService(service)).
                passwordEncoder(passwordEncoder);
	}

	private UserDetailsService userDetailsService(UserService userService) {
		return (userName) ->{
			CustomUserDetails customUserDetails = userService.getCustomerUserDetailsByUserName(userName);
			return customUserDetails;
		};
	}
}
