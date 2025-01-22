package ru.globux.tacos.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.globux.tacos.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

  User findByUsername(String username);
}
