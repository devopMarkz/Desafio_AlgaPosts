package com.devopMarkz.post_service.infraestructure.controller;

import com.devopMarkz.post_service.application.usecases.*;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final CriarPostUseCase criarPostUseCase;
    private final BuscarPostPorIdUseCase buscarPostPorIdUseCase;
    private final BuscarPostsPaginadoUseCase buscarPostsPaginadoUseCase;

    public PostController(CriarPostUseCase criarPostUseCase, BuscarPostPorIdUseCase buscarPostPorIdUseCase, BuscarPostsPaginadoUseCase buscarPostsPaginadoUseCase) {
        this.criarPostUseCase = criarPostUseCase;
        this.buscarPostPorIdUseCase = buscarPostPorIdUseCase;
        this.buscarPostsPaginadoUseCase = buscarPostsPaginadoUseCase;
    }

    @PostMapping
    public ResponseEntity<PostOutput> criarPost(@RequestBody @Valid PostInput input) {
        PostOutput postOutput = criarPostUseCase.execute(input);
        return ResponseEntity.created(URI.create("/api/posts/" + postOutput.getId())).body(postOutput);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostOutput> buscarPorId(@PathVariable UUID id) {
        PostOutput output = buscarPostPorIdUseCase.execute(id);
        return ResponseEntity.ok(output);
    }

    @GetMapping
    public ResponseEntity<Page<PostSummaryOutput>> buscaPaginada(
            @RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize
    ) {
        Page<PostSummaryOutput> postSummaryOutputPage = buscarPostsPaginadoUseCase.execute(pageNumber, pageSize);
        return ResponseEntity.ok(postSummaryOutputPage);
    }

}
