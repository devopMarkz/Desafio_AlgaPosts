package com.devopMarkz.post_service.infraestructure.config;

import com.devopMarkz.post_service.application.usecases.PostQueueInput;
import com.devopMarkz.post_service.domain.entities.Post;
import com.devopMarkz.post_service.domain.repository.PostRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Component
public class RabbitMQListener {

    private final PostRepository postRepository;

    public RabbitMQListener(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Transactional
    @RabbitListener(queues = RabbitMQConfig.POST_PROCESSING_RESULT_QUEUE)
    public void processarResult(PostQueueInput input) {
        log.info("Result recebido: {id: {}, wordCount: {}, calculatedValue: {}}", input.getPostId(), input.getWordCount(), input.getCalculatedValue());

        Post post = postRepository.buscarPorId(UUID.fromString(input.getPostId())).orElseThrow();
        post.registrarProcessamento(input.getWordCount(), input.getCalculatedValue());

        log.info("Post atualizado.");
    }

}
