package com.devopMarkz.post_service.infraestructure.config;

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
    public static final String POST_PROCESSING_RESULT_DLX = "post-service.post-processing-result.v1.dlx";
    public static final String POST_PROCESSING_RESULT_DLQ = "post-service.post-processing-result.v1.dlq";
    public static final String POST_PROCESSING_RESULT_DLQ_ROUTING_KEY = "key.post.result-processing.dlq";

    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory){
        return new RabbitAdmin(connectionFactory);
    }

    @Bean
    public JacksonJsonMessageConverter messageConverter() {
        return new JacksonJsonMessageConverter();
    }

    @Bean
    public Queue postProcessingResultQueue() {
        return QueueBuilder
                .durable(POST_PROCESSING_RESULT_QUEUE)
                .deadLetterExchange(POST_PROCESSING_RESULT_DLX)
                .deadLetterRoutingKey(POST_PROCESSING_RESULT_DLQ_ROUTING_KEY)
                .build();
    }

    @Bean
    public DirectExchange postProcessingResultExchange() {
        return ExchangeBuilder
                .directExchange(POST_PROCESSING_RESULT_EXCHANGE)
                .build();
    }

    @Bean
    public Queue postProcessingResultDlq() {
        return QueueBuilder.durable(POST_PROCESSING_RESULT_DLQ).build();
    }

    @Bean
    public DirectExchange postProcessingResultDlx() {
        return ExchangeBuilder
                .directExchange(POST_PROCESSING_RESULT_DLX)
                .build();
    }

    @Bean
    public Binding bindingPostProcessing() {
        return BindingBuilder
                .bind(postProcessingResultQueue())
                .to(postProcessingResultExchange())
                .with("key.post.result-processing");
    }

    @Bean
    public Binding bindingPostProcessingResultDlq() {
        return BindingBuilder
                .bind(postProcessingResultDlq())
                .to(postProcessingResultDlx())
                .with(POST_PROCESSING_RESULT_DLQ_ROUTING_KEY);
    }

}
