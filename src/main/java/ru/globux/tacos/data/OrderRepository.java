package ru.globux.tacos.data;

import org.springframework.data.repository.CrudRepository;
import ru.globux.tacos.TacoOrder;

import java.util.UUID;

public interface OrderRepository extends CrudRepository<TacoOrder, UUID> {

}
