package com.mariapuri.mydom.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class MydomConfigServerApplication {

	public static void main(String[] args) {
		DotenvInitializer.init();

//		new SpringApplicationBuilder(MydomConfigServerApplication.class)
//					.initializers(new VaultSecretsInitializer())
//					.run(args);
		SpringApplication.run(MydomConfigServerApplication.class, args);
	}

}
