package ru.globux.tacos.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation
        .authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web
        .builders.HttpSecurity;
import org.springframework.security.config.annotation.web
        .configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authManReqMatchReg -> authManReqMatchReg
                        .requestMatchers(HttpMethod.OPTIONS).permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/ingredients").permitAll()
                        .requestMatchers("/api/tacos", "/api/orders/**").permitAll()
                        .requestMatchers(HttpMethod.PATCH, "/api/ingredients").permitAll()
                        .requestMatchers("/**").permitAll())
                .formLogin(httpSecFormLoginConf -> httpSecFormLoginConf
                        .loginPage("/login"))
                .httpBasic(name -> name
                        .realmName("Taco Cloud"))
                .logout(logout -> logout
                        .logoutSuccessUrl("/"))
                .csrf(matcher -> matcher
                        .ignoringRequestMatchers("/h2-console/**", "/api/**"))
                // Allow pages to be loaded in frames from the same origin; needed for H2-Console
                .headers(matcher -> matcher
                        .frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin));
        return http.build();
    }

    @Bean
    public PasswordEncoder encoder() {
//    return new StandardPasswordEncoder("53cr3t");
        return NoOpPasswordEncoder.getInstance();
    }


    @Bean
    AuthenticationManager authenticationManager(UserDetailsService userDetailsService,
                                                HttpSecurity http, PasswordEncoder encoder) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        return authenticationManagerBuilder.userDetailsService(userDetailsService)
                .passwordEncoder(encoder).and().build();
    }
}


