package ru.globux.tacos.messaging;

import ru.globux.tacos.TacoOrder;

public interface OrderMessagingService {

  void sendOrder(TacoOrder order);
  
}
