package ru.globux.tacos.data;

import org.springframework.data.repository.CrudRepository;
import ru.globux.tacos.TacoOrder;

public interface OrderRepository extends CrudRepository<TacoOrder, Long> {

}
