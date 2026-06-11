package com.devopMarkz.post_service.application.usecases;

import com.devopMarkz.post_service.domain.entities.Post;
import com.devopMarkz.post_service.domain.repository.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class BuscarPostPorIdUseCase {

    private final PostRepository repository;

    public BuscarPostPorIdUseCase(PostRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public PostOutput execute(UUID id) {
        Post post = repository.buscarPorId(id).orElseThrow();
        return montarPostOutput(post);
    }

    private PostOutput montarPostOutput(Post post) {
        return new PostOutput(post.getId().toString(), post.getTitle(), post.getBody(), post.getAuthor(), post.getWordCount(), post.getCalculatedValue());
    }
}
