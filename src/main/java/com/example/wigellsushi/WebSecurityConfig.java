package com.example.wigellsushi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .requestMatchers("/api/v3/dishes").hasRole("USER")
                .requestMatchers("/api/v3/bookroom").hasRole("USER")
                .requestMatchers("/api/v3/updatebooking").hasRole("USER")
                .requestMatchers("/api/v3/mybookings/{id}").hasRole("USER")
                .requestMatchers("/api/v3/customers").hasRole("ADMIN")
                .requestMatchers("/api/v3/add-dish").hasRole("ADMIN")
                .requestMatchers("/api/v3/deletedish").hasRole("ADMIN")
                .requestMatchers("/api/v3/updateroom").hasRole("ADMIN")
                .requestMatchers("/api/v3/allrooms").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .httpBasic();

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user =
                User.builder()
                        .username("Jsson")
                        .password(passwordEncoder().encode("jsson"))
                        .roles("USER")
                        .build();

        UserDetails admin =
                User.builder()
                        .username("jsson1")
                        .password(passwordEncoder().encode("jsson1"))
                        .roles("ADMIN")
                        .build();

        return new InMemoryUserDetailsManager(user, admin);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
