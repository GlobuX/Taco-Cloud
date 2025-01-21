package ru.globux.tacos.data;

import org.springframework.data.repository.CrudRepository;
import ru.globux.tacos.User;

public interface UserRepository extends CrudRepository<User, Long> {

  User findByUsername(String username);
  
}
