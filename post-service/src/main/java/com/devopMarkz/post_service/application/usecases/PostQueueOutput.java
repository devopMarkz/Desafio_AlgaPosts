package com.devopMarkz.post_service.application.usecases;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PostQueueOutput {

    private String postId;
    private String postBody;

}
