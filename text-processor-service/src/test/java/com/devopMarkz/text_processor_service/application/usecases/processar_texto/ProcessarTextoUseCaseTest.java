package com.devopMarkz.text_processor_service.application.usecases.processar_texto;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ProcessarTextoUseCaseTest {

    private final ProcessarTextoUseCase useCase = new ProcessarTextoUseCase();

    @Test
    void deveContarPalavrasECalcularValorEstimado() {
        String postId = UUID.randomUUID().toString();

        ProcessarTextoOutput output = useCase.execute(new ProcessarTextoInput(postId, "um texto com cinco palavras"));

        assertThat(output.getPostId()).isEqualTo(postId);
        assertThat(output.getWordCount()).isEqualTo(5);
        assertThat(output.getCalculatedValue()).isEqualByComparingTo(new BigDecimal("0.50"));
    }

    @Test
    void deveRetornarZeroQuandoTextoEstiverEmBranco() {
        String postId = UUID.randomUUID().toString();

        ProcessarTextoOutput output = useCase.execute(new ProcessarTextoInput(postId, "   "));

        assertThat(output.getWordCount()).isZero();
        assertThat(output.getCalculatedValue()).isEqualByComparingTo(BigDecimal.ZERO);
    }

    @Test
    void deveRejeitarIdInvalido() {
        assertThatThrownBy(() -> useCase.execute(new ProcessarTextoInput("id-invalido", "texto")))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
