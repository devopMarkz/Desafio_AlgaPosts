package com.devopMarkz.post_service.domain.repository;

import com.devopMarkz.post_service.domain.entities.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface PostRepository {

    Post salvar(Post post);

    Optional<Post> buscarPorId(UUID id);

    Page<Post> buscaPaginada(int pageNumber, int pageSize);

}
