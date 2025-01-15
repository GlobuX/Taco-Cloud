package ru.globux.tacos.authorization.users;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<String, Long> {

    User findbyUserName(Long id);
    }
}
