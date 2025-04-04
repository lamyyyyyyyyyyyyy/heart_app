package com.notification_Service.notification_Service.Service;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String QUEUE = "notificationQueue";
    public static final String EXCHANGE = "appointmentExchange";
    public static final String ROUTING_KEY = "appointment.notification";

    @Bean
    public Queue notificationQueue() {
        return new Queue(QUEUE);
    }

    @Bean
    public TopicExchange appointmentExchange() {
        return new TopicExchange(EXCHANGE);
    }

    @Bean
    public Binding binding(Queue notificationQueue, TopicExchange appointmentExchange) {
        return BindingBuilder.bind(notificationQueue).to(appointmentExchange).with(ROUTING_KEY);
    }
}