package com.devopMarkz.post_service.application.usecases;

import com.devopMarkz.post_service.domain.entities.Post;
import com.devopMarkz.post_service.domain.repository.PostRepository;
import com.devopMarkz.post_service.infraestructure.config.RabbitMQConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class CriarPostUseCase {

    private final PostRepository postRepository;
    private final RabbitTemplate rabbitTemplate;

    public CriarPostUseCase(PostRepository postRepository, RabbitTemplate rabbitTemplate) {
        this.postRepository = postRepository;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Transactional
    public PostOutput execute(PostInput input) {
        Post post = new Post(input.getTitle(), input.getBody(), input.getAuthor());
        postRepository.salvar(post);

        PostOutput postOutput = montarPostOutput(post);

        PostQueueOutput postQueueOutput = montarPostQueueOutput(post);
        rabbitTemplate.convertAndSend(RabbitMQConfig.POST_PROCESSING_EXCHANGE, "key.post.processing", postQueueOutput);
        log.info("Post de id {} enviado.", postQueueOutput.getPostId());

        return postOutput;
    }

    private PostOutput montarPostOutput(Post post) {
        return new PostOutput(post.getId().toString(), post.getTitle(), post.getBody(), post.getAuthor());
    }

    private PostQueueOutput montarPostQueueOutput(Post post) {
        return new PostQueueOutput(post.getId().toString(), post.getBody());
    }
}
