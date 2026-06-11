package com.devopMarkz.post_service.domain.entities;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PostTest {

    @Test
    void deveCriarPostComDadosObrigatorios() {
        Post post = new Post("Titulo", "Corpo do post", "Marcos");

        assertThat(post.getTitle()).isEqualTo("Titulo");
        assertThat(post.getBody()).isEqualTo("Corpo do post");
        assertThat(post.getAuthor()).isEqualTo("Marcos");
    }

    @Test
    void deveRejeitarTituloEmBranco() {
        assertThatThrownBy(() -> new Post(" ", "Corpo do post", "Marcos"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Titulo precisa ser preenchido.");
    }

    @Test
    void deveRegistrarResultadoDoProcessamento() {
        Post post = new Post("Titulo", "Corpo do post", "Marcos");

        post.registrarProcessamento(3, new BigDecimal("0.30"));

        assertThat(post.getWordCount()).isEqualTo(3);
        assertThat(post.getCalculatedValue()).isEqualByComparingTo("0.30");
    }

    @Test
    void deveGerarResumoComAsTresPrimeirasLinhas() {
        Post post = new Post("Titulo", "linha 1\nlinha 2\nlinha 3\nlinha 4", "Marcos");

        assertThat(post.obterResumo()).isEqualTo("linha 1\nlinha 2\nlinha 3");
    }
}
