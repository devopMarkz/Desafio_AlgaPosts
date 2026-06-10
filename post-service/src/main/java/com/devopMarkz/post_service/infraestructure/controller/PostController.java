package com.devopMarkz.post_service.infraestructure.controller;

import com.devopMarkz.post_service.application.usecases.CriarPostUseCase;
import com.devopMarkz.post_service.application.usecases.PostInput;
import com.devopMarkz.post_service.application.usecases.PostOutput;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final CriarPostUseCase criarPostUseCase;

    public PostController(CriarPostUseCase criarPostUseCase) {
        this.criarPostUseCase = criarPostUseCase;
    }

    @PostMapping
    public ResponseEntity<PostOutput> criarPost(@RequestBody @Valid PostInput input) {
        PostOutput postOutput = criarPostUseCase.execute(input);
        return ResponseEntity.created(URI.create("/api/posts/" + postOutput.getId())).body(postOutput);
    }

}
