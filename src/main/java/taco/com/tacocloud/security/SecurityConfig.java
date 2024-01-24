package taco.com.tacocloud.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import taco.com.tacocloud.data.UserRepository;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

import taco.com.tacocloud.models.User;

@Configuration
public class SecurityConfig {
    

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepo){
        return username->{
            User user = userRepo.findByUsername(username);
            if( user != null) return user;
            throw new UsernameNotFoundException("User'"+username+"' not found");
        };
    }

    
}
