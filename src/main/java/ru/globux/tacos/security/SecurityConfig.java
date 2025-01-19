package ru.globux.tacos.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity(debug = true)
@Configuration
public class SecurityConfig {
    private final UserDetailsService userDetailsService;

    public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authManReqMatchReg -> authManReqMatchReg
                    //.requestMatchers("/design", "/orders").hasRole("USER")
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
                    .ignoringRequestMatchers("/actuator", "/h2-console/**", "/api/**"))
            .headers(matcher -> matcher
                    .frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin));
        return http.build();
    }

    @Bean
    AuthenticationManager authenticationManager(
            AuthenticationManagerBuilder builder, PasswordEncoder encoder) throws Exception {
        return builder.userDetailsService(userDetailsService).passwordEncoder(encoder()).and().build();
    }
}
