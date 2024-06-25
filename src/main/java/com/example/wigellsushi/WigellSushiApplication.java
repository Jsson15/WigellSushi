package com.example.wigellsushi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class WigellSushiApplication {

	public static void main(String[] args) {
		SpringApplication.run(WigellSushiApplication.class, args);
	}

}
