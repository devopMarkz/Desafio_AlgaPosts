package com.devopMarkz.post_service.domain.entities;

import jakarta.persistence.*;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
@Table(name = "tb_post")
@Getter
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "body", nullable = false, columnDefinition = "TEXT")
    private String body;

    @Column(name = "author", nullable = false)
    private String author;

    @Column(name = "word_count")
    private Integer wordCount;

    @Column(name = "calculated_value")
    private BigDecimal calculatedValue;

    public Post(String title, String body, String author) {
        validarTitle(title);
        validarBody(body);
        validarAuthor(author);

        this.title = title;
        this.body = body;
        this.author = author;
    }

    protected Post() {
    }

    public void registrarProcessamento(Integer wordCount, BigDecimal calculatedValue) {
        this.wordCount = wordCount;
        this.calculatedValue = calculatedValue;
    }

    public void alterarTitle(String title) {
        validarTitle(title);
        this.title = title;
    }

    public void alterarBody(String body) {
        validarBody(body);
        this.body = body;
    }

    public String obterResumo() {
        String[] linhas = body.split("\\R");

        return Arrays.stream(linhas)
                .limit(3)
                .collect(Collectors.joining("\n"));
    }

    private void validarTitle(String title) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Título precisa ser preenchido.");
        }
    }

    private void validarBody(String body) {
        if(body == null || body.isBlank()) {
            throw new IllegalArgumentException("Body precisa ser preenchido.");
        }
    }

    private void validarAuthor(String author) {
        if(author == null || author.isBlank()) {
            throw new IllegalArgumentException("Author precisa ser preenchido.");
        }
    }
}
