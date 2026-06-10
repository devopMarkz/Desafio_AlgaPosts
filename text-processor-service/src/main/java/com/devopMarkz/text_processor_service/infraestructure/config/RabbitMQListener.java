package com.devopMarkz.text_processor_service.infraestructure.config;

import com.devopMarkz.text_processor_service.application.usecases.processar_texto.ProcessarTextoInput;
import com.devopMarkz.text_processor_service.application.usecases.processar_texto.ProcessarTextoOutput;
import com.devopMarkz.text_processor_service.application.usecases.processar_texto.ProcessarTextoUseCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class RabbitMQListener {

    private final ProcessarTextoUseCase processarTextoUseCase;
    private final RabbitTemplate rabbitTemplate;

    public RabbitMQListener(ProcessarTextoUseCase processarTextoUseCase, RabbitTemplate rabbitTemplate) {
        this.processarTextoUseCase = processarTextoUseCase;
        this.rabbitTemplate = rabbitTemplate;
    }

    @RabbitListener(queues = RabbitMQConfig.POST_PROCESSING_QUEUE)
    public void processarPost(ProcessarTextoInput input) {
        log.info("Post de id {} recebido.", input.getPostId());
        ProcessarTextoOutput output = processarTextoUseCase.execute(input);

        rabbitTemplate.convertAndSend(
                RabbitMQConfig.POST_PROCESSING_RESULT_EXCHANGE,
                "key.post.result-processing",
                output);
        log.info("Resultado enviado para fila de result: {id: {}, wordCount: {}, calculatedValue: {}}", output.getPostId(), output.getWordCount(), output.getCalculatedValue());
    }
}
