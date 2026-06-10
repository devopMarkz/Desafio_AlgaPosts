package com.devopMarkz.post_service.infraestructure.persistence;

import com.devopMarkz.post_service.domain.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpringDataPostRepository extends JpaRepository<Post, UUID> {
}
