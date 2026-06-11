package com.devopMarkz.text_processor_service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = {
		"app.rabbitmq.initialize=false",
		"spring.rabbitmq.listener.simple.auto-startup=false",
		"spring.rabbitmq.listener.direct.auto-startup=false"
})
class TextProcessorServiceApplicationTests {

	@Test
	void contextLoads() {
	}

}
