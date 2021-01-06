package br.com.sboot.jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"br.com.sboot.jpa.*.*"})
public class SbootJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SbootJpaApplication.class, args);
	}

}
