package com.devopMarkz.post_service.application.usecases;

import com.devopMarkz.post_service.domain.entities.Post;
import com.devopMarkz.post_service.domain.repository.PostRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BuscarPostsPaginadoUseCase {

    private final PostRepository repository;

    public BuscarPostsPaginadoUseCase(PostRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public Page<PostSummaryOutput> execute(int pageNumber, int pageSize) {
        Page<Post> posts = repository.buscaPaginada(pageNumber, pageSize);
        return posts.map(post -> new PostSummaryOutput(post.getId().toString(), post.getTitle(), post.obterResumo(), post.getAuthor()));
    }

}
