package ru.globux.tacos.messaging;

import jakarta.jms.JMSException;
import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ArtemisConfig {

    @Bean
    CachingConnectionFactory connectionFactory(@Qualifier("artemisConnectionFactory") ActiveMQConnectionFactory connectionFactory) {
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory(connectionFactory);
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        return cachingConnectionFactory;
    }

    @Bean
    ActiveMQConnectionFactory artemisConnectionFactory() throws JMSException {
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory();
        factory.setUser("artemis");
        factory.setPassword("artemis");
        factory.setBrokerURL("tcp://localhost:61616");
        return factory;
    }
}
