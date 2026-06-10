package com.devopMarkz.post_service.infraestructure.persistence;

import com.devopMarkz.post_service.domain.entities.Post;
import com.devopMarkz.post_service.domain.repository.PostRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class JpaPostRepository implements PostRepository {

    private final SpringDataPostRepository repository;

    public JpaPostRepository(SpringDataPostRepository repository) {
        this.repository = repository;
    }

    @Override
    public Post salvar(Post post) {
        return repository.save(post);
    }

    @Override
    public Optional<Post> buscarPorId(UUID id) {
        return repository.findById(id);
    }
}
