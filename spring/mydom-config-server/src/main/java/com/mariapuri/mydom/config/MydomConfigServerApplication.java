package com.mariapuri.mydom.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class MydomConfigServerApplication {

	public static void main(String[] args) {
		DotenvInitializer.init();
		SpringApplication.run(MydomConfigServerApplication.class, args);
	}

}
