package com.devopMarkz.text_processor_service.infraestructure.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String POST_PROCESSING_QUEUE = "text-processor-service.post-processing.v1.q";
    public static final String POST_PROCESSING_RESULT_QUEUE = "post-service.post-processing-result.v1.q";

    public static final String POST_PROCESSING_EXCHANGE = "text-processor-service.post-processing.v1.e";
    public static final String POST_PROCESSING_RESULT_EXCHANGE = "post-service.post-processing-result.v1.e";

    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }

    @Bean
    public JacksonJsonMessageConverter messageConverter() {
        return new JacksonJsonMessageConverter();
    }

    @Bean
    public Queue postProcessingQueue() {
        return QueueBuilder.durable(POST_PROCESSING_QUEUE).build();
    }

    @Bean
    public DirectExchange postProcessingExchange() {
        return ExchangeBuilder
                .directExchange(POST_PROCESSING_EXCHANGE)
                .build();
    }

    @Bean
    public Binding bindingPostProcessing() {
        return BindingBuilder
                .bind(postProcessingQueue())
                .to(postProcessingExchange())
                .with("key.post.processing");
    }

}
