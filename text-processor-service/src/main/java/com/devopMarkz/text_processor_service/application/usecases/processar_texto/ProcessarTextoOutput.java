package com.devopMarkz.text_processor_service.application.usecases.processar_texto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class ProcessarTextoOutput {

    private String postId;
    private Integer wordCount;
    private BigDecimal calculatedValue;

}
