package com.devopMarkz.post_service.application.usecases;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostSummaryOutput {

    private String id;
    private String title;
    private String summary;
    private String author;

}