package br.com.springcloud.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class SpringCloudDemoGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudDemoGatewayApplication.class, args);
	}

}
