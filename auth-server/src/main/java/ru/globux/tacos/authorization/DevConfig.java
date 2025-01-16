package ru.globux.tacos.authorization;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.globux.tacos.authorization.users.User;
import ru.globux.tacos.authorization.users.UserRepository;

@Configuration
public class DevConfig {

    @Bean
    public ApplicationRunner dataLoader(UserRepository userRepo, PasswordEncoder encoder) {
        return args -> {
            userRepo.save(new User("zxcv",
                    encoder.encode("zxcv"),
                    "ROLE_ADMIN"));
            userRepo.save(new User("asdf",
                    encoder.encode("asdf"),
                    "ROLE_ADMIN"));
            userRepo.save(new User("qwer",
                    encoder.encode("qwer"),
                    "ROLE_ADMIN"));
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
