package com.example.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class CustomerOrderApigatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerOrderApigatewayApplication.class, args);
	}

}
