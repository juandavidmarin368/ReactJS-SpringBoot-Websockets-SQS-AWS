package com.websockets.WebSockets;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class WebSocketsApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebSocketsApplication.class, args);
	}

}
