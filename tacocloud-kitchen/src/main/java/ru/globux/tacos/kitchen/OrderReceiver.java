package ru.globux.tacos.kitchen;

import ru.globux.tacos.TacoOrder;

public interface OrderReceiver {

  TacoOrder receiveOrder();

}