package taco.com.tacocloud.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

import taco.com.tacocloud.models.User;
import taco.com.tacocloud.repositories.UserRepository;

import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
@Configuration
@EnableGlobalMethodSecurity
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

    @Bean  
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.authorizeRequests()
        .antMatchers("/design", "/orders").access("hasRole('USER')")
        .antMatchers("/", "/**").access("permitAll()")
        .and().formLogin().loginPage("/login").loginProcessingUrl("/authenticate")
        .usernameParameter("user")
        .passwordParameter("pwd") // Call login page "login.html"
        .and().build(); }


    
}
