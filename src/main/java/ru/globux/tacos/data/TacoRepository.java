package ru.globux.tacos.data;

import org.springframework.data.repository.CrudRepository;
import ru.globux.tacos.Taco;

public interface TacoRepository extends CrudRepository<Taco, Long> {
}
