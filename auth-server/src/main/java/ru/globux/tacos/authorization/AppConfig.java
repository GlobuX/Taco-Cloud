package ru.globux.tacos.authorization;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import ru.globux.tacos.authorization.users.UserRepository;

@Configuration
@EnableWebSecurity
public class AppConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests(authReq ->
                    authReq.anyRequest().authenticated())
                .formLogin(Customizer.withDefaults()).build();
    }

    @Bean
    UserDetailsService userDetailsService(UserRepository userRepo) {
        return userRepo::findByUsername;
    }
}
