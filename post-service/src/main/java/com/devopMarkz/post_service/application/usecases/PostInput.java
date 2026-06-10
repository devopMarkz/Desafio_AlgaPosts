package com.devopMarkz.post_service.application.usecases;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostInput {

    @NotBlank(message = "Title não pode estar vazio.")
    private String title;

    @NotBlank(message = "Body não pode estar vazio.")
    private String body;

    @NotBlank(message = "Author não pode estar vazio.")
    private String author;

}
