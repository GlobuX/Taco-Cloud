package ru.globux.tacos.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import ru.globux.tacos.User;
import ru.globux.tacos.data.UserRepository;

import java.util.ArrayList;
import java.util.List;

@EnableWebSecurity(debug = true)
@Configuration
public class SecurityConfig {
    private UserDetailsService userDetailsService;

    public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authManReqMatchReg -> authManReqMatchReg
                    .requestMatchers("/design", "/orders").hasRole("USER")
                    .requestMatchers("/", "/**").permitAll())
            .formLogin(httpSecFormLoginConf -> httpSecFormLoginConf
                    .loginPage("/login"))
            .logout(logout -> logout
                    .logoutSuccessUrl("/"))
            .csrf(matcher -> matcher
                    .ignoringRequestMatchers("/design**")
                    .ignoringRequestMatchers("/h2-console/**")
                    .ignoringRequestMatchers("/actuator"))
            .headers(matcher -> matcher
                    .frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin));
        return http.build();
    }


//    @Bean
//    public UserDetailsService userDetailsService(PasswordEncoder encoder) {
//        List<UserDetails> userList = new ArrayList<>();
//        userList.add(new User("buzz", encoder.encode("pass"),
//                List.of(new SimpleGrantedAuthority("ROLE_USER"))));
//        userList.add(new User("woody", encoder.encode("pass"),
//                List.of(new SimpleGrantedAuthority("ROLE_USER"))));
//        userList.add(new User("glob", encoder.encode("pass"),
//                List.of(new SimpleGrantedAuthority("ROLE_USER"))));
//        return new InMemoryUserDetailsManager(userList);
//    }

}
