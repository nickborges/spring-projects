package br.com.springcloud.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableFeignClients
@EnableCircuitBreaker
public class SpringCloudDemoUsuarioApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(SpringCloudDemoUsuarioApplication.class, args);
	}
	
	@Bean
	@LoadBalanced //Ribbon load balancer
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

}
