package com.devopMarkz.text_processor_service.application.usecases.processar_texto;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class ProcessarTextoUseCase {

    public ProcessarTextoOutput execute(ProcessarTextoInput input) {

        UUID.fromString(input.getPostId());

        String texto = input.getPostBody().trim();

        if (texto.isEmpty()) {
            return new ProcessarTextoOutput(
                    input.getPostId(),
                    0,
                    BigDecimal.ZERO
            );
        }

        String[] palavras = texto.split("\\s+");

        int quantidadePalavras = palavras.length;

        BigDecimal valorEstimado = BigDecimal.valueOf(quantidadePalavras).multiply(new BigDecimal("0.10"));

        return new ProcessarTextoOutput(
                input.getPostId(),
                quantidadePalavras,
                valorEstimado
        );
    }
}
