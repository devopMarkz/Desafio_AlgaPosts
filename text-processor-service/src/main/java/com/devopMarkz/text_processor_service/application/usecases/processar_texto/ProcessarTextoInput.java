package com.devopMarkz.text_processor_service.application.usecases.processar_texto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProcessarTextoInput {

    private String postId;
    private String postBody;

}