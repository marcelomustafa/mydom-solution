package com.mariapuri.mydom.auth;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class MydomAuthServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MydomAuthServiceApplication.class, args);
	}

	@PostConstruct
	public void init() {
		System.out.println("🔍 Banco URL (PostConstruct): " + System.getProperty("ORACLE_URL"));
	}

	@EventListener(ApplicationReadyEvent.class)
	public void afterStartup() {
		System.out.println("✅ Application is ready. Vault value: " + System.getProperty("ORACLE_URL"));
	}

}
