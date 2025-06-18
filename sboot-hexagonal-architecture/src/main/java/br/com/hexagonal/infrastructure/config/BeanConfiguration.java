package br.com.hexagonal.infrastructure.config;

import br.com.hexagonal.application.Application;
import br.com.hexagonal.domain.BankAccountService;
import br.com.hexagonal.infrastructure.component.BankAccountComponent;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan(basePackageClasses = Application.class)
@EnableJpaRepositories(basePackages = "br.com.hexagonal.infrastructure.*")
@EntityScan(basePackages="br.com.hexagonal.infrastructure.*")
public class BeanConfiguration {

    @Bean
    public BankAccountService bankAccountService(BankAccountComponent repository) {
        return new BankAccountService(repository, repository);
    }
}