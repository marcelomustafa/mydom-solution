package com.mariapuri.mydom.discovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class MydomDiscoveryServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MydomDiscoveryServerApplication.class, args);
	}

}
