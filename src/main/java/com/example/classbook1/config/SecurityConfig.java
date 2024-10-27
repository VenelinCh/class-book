package com.example.classbook1.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private MyUserDataService userDetailsService;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        return http
                .authorizeHttpRequests(registry -> {
                    registry.requestMatchers("/").permitAll();

//                    registry.requestMatchers("/**").hasRole("PARENT");//remove it
                    registry.requestMatchers("/parents").hasAuthority("TEACHER");
                    registry.requestMatchers("/parents/my-profile").hasAuthority("PARENT");
                    registry.requestMatchers("/parents/marks/**").hasAuthority("PARENT");
                    registry.requestMatchers("/parents/marks/**").hasAuthority("PARENT");
                    registry.requestMatchers("/parents/a/**").hasAuthority("ADMIN");
                    registry.requestMatchers("/directors/a/**").hasAuthority("ADMIN");
                    registry.requestMatchers("/directors").permitAll();
                    registry.requestMatchers("/students").hasAuthority("TEACHER");
                    registry.requestMatchers("/students/a/**").hasAuthority("ADMIN");
                    //registry.requestMatchers("/parents/{id}").hasAuthority("PARENT");

//                    registry.requestMatchers("/directors/**").hasAuthority("DIRECTOR");
//                    registry.requestMatchers("/teachers/**").hasAuthority("TEACHER");
//                    registry.requestMatchers("/students/**").hasAuthority("STUDENT");
                    registry.anyRequest().authenticated();
                })
                .formLogin(formLogin -> formLogin.permitAll())
                .build();
    }

    @Bean
    public UserDetailsService userDetailService(){
        return userDetailsService;
    }
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        //provider.setPasswordEncoder();
        return provider;
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}