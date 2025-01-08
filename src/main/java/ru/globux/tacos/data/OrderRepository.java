package ru.globux.tacos.data;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import ru.globux.tacos.TacoOrder;
import ru.globux.tacos.User;

import java.util.List;

public interface OrderRepository extends CrudRepository<TacoOrder, Long> {

    List<TacoOrder> findByUserOrderByCreatedAtDesc(@AuthenticationPrincipal User user, Pageable pageable);
}
