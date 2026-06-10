package com.devopMarkz.post_service.application.usecases;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
public class PostQueueInput {

    private String postId;
    private Integer wordCount;
    private BigDecimal calculatedValue;

}
