package com.example.wigellsushi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.builder()
                .username("Jsson")
                .password("jsson")
                .roles("USER")
                .build();

        UserDetails admin = User.builder()
                .username("jsson1")
                .password("jsson1")
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user, admin);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .requestMatchers("/api/v3/customers").hasRole("ADMIN")
                .requestMatchers("/api/v3/add-dish").hasRole("ADMIN")
                .requestMatchers("/api/v3/deletedish/**").hasRole("ADMIN")
                .requestMatchers("/api/v3/updateroom/**").hasRole("ADMIN")
                .requestMatchers("/api/v3/allrooms").hasRole("ADMIN")
                .requestMatchers("/api/v3/**").hasRole("USER")
                .anyRequest().authenticated()
                .and()
                .httpBasic();

        return http.build();
    }
}
