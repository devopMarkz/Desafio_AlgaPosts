package com.devopMarkz.text_processor_service.infraestructure.config;

import jakarta.annotation.PostConstruct;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQInitializer {

    private final RabbitAdmin rabbitAdmin;

    public RabbitMQInitializer(RabbitAdmin rabbitAdmin) {
        this.rabbitAdmin = rabbitAdmin;
    }

    @PostConstruct
    public void execute() {
        rabbitAdmin.initialize();
    }

}
