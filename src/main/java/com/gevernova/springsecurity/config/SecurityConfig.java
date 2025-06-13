package com.gevernova.springsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // to make this class as a configuration class
@EnableWebSecurity //
public class SecurityConfig {
    @Bean // UserDetailsService Bean

    // stored in memory
    public UserDetailsService userDetailsService(){
        UserDetails user1 = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("password1"))
                .roles("ADMIN")
                .build();

        UserDetails user2 = User.builder()
                .username("user2")
                .password(passwordEncoder().encode("password2"))
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user1, user2); // stores them for authentication
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(); //encrypt passwords
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        // configuring HTTP security for different URLs
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/public").permitAll() // anyone can access this
                        .requestMatchers("/api/user").hasRole("USER") // only users with "USER" role
                        .requestMatchers("/api/admin").hasRole("ADMIN") // only users with "ADMIN" role
                        .anyRequest().authenticated() // everything else needs login
                )
                // Enables form-based login
                .formLogin(form -> form.defaultSuccessUrl("/api/private", true))
                .httpBasic(basic -> {});
                return http.build();
    }
}
