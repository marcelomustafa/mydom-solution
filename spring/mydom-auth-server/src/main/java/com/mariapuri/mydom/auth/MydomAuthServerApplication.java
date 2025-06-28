package com.mariapuri.mydom.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MydomAuthServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MydomAuthServerApplication.class, args);
	}

}
