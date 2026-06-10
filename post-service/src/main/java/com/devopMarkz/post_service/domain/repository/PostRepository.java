package com.devopMarkz.post_service.domain.repository;

import com.devopMarkz.post_service.domain.entities.Post;

import java.util.Optional;
import java.util.UUID;

public interface PostRepository {

    Post salvar(Post post);

    Optional<Post> buscarPorId(UUID id);

}
