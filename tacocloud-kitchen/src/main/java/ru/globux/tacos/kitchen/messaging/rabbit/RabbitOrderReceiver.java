package ru.globux.tacos.kitchen.messaging.rabbit;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import ru.globux.tacos.TacoOrder;
import ru.globux.tacos.kitchen.OrderReceiver;

@Profile("rabbitmq-template")
@Component("templateOrderReceiver")
public class RabbitOrderReceiver implements OrderReceiver {

  private RabbitTemplate rabbit;

  public RabbitOrderReceiver(RabbitTemplate rabbit) {
    this.rabbit = rabbit;
  }

  public TacoOrder receiveOrder() {
    return (TacoOrder) rabbit.receiveAndConvert("tacocloud.order.queue");
  }

}
