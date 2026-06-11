package com.devopMarkz.post_service.application.usecases;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostOutput {

    private String id;
    private String title;
    private String body;
    private String author;
    private Integer wordCount;
    private BigDecimal calculatedValue;

    public PostOutput(String id, String title, String body, String author) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.author = author;
    }

    public PostOutput(String id, String title, String body, String author, Integer wordCount, BigDecimal calculatedValue) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.author = author;
        this.wordCount = wordCount;
        this.calculatedValue = calculatedValue;
    }
}
